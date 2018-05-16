package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Decoder;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONArray;
//import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
//import com.amazonaws.services.budgets.model.Notification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.model.Comment;
import com.example.demo.model.Friend;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.Notification;

import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UploadToS3;

@Controller
@Component
public class ProfileController {

	@Autowired
	UploadToS3 s3;

	@Autowired
	UserRepository userepo;

	@Autowired
	PostRepository postrepo;

	@Autowired
	FriendRepository frirepo;

	@Autowired
	CommentRepository commentrepo;

	@Autowired
	NotificationRepository notifyrepo;

	@GetMapping
	public ModelAndView renderPage() {
		ModelAndView indexPage = new ModelAndView();
		indexPage.setViewName("FacebookLogin");
		return indexPage;

	}

	@PostMapping(value = "/upload")
	public ModelAndView uploadToS3(@RequestParam("file") MultipartFile image,
			@RequestParam(name = "username") String username, @RequestParam(name = "desc") String desc,
			HttpServletRequest req) throws Exception {

		ModelAndView profilePage = new ModelAndView();
		String imgSrc = s3.upload(image.getOriginalFilename(), image.getInputStream());
		String uid = (String) req.getSession().getAttribute("myID");
		User u = userepo.findByuserId(uid);
		u.setProfilephoto(imgSrc);
		u.setDescription(desc);
		u.setName(username);
		u.setDescription(desc);
		userepo.save(u);
		profilePage.addObject("user", u);

		List<Post> posts = postrepo.findByuser(u);
		profilePage.addObject("post", posts);
		profilePage.setViewName("profile");
		return profilePage;

	}

	@PostMapping(value = "/friends")
	public ModelAndView getAllFriends(HttpServletRequest req) {
		ArrayList<String> al = new ArrayList<String>();

		ModelAndView mv = new ModelAndView();
		String uid = (String) req.getSession().getAttribute("myID");
		User u = userepo.findByuserId(uid);
		List<Friend> friends = frirepo.findByuser(u);
		for (int i = 0; i < friends.size(); i++) {
			String friendId = friends.get(i).getFriendId();
			al.add(friendId);
		}
		List<User> friendls = userepo.findByuserIdIn(al);
		System.out.println("hererererere " + friendls);
		mv.addObject("friends", friendls);
		// mv.addObject("nm", nm);
		mv.setViewName("ListOfFriends");
		return mv;

	}

	@PostMapping(value = "/viewNotify")
	public ModelAndView notifyFriend(HttpServletRequest req) {
		ArrayList<String> al = new ArrayList<String>();
		ModelAndView mv = new ModelAndView();
		String uid = (String) req.getSession().getAttribute("myID");
		User u = userepo.findByuserId(uid);
		List<Notification> notify = notifyrepo.findByuser(u);
		mv.addObject("notify", notify);
		mv.setViewName("DisplayNotification");
		return mv;

	}

	@PostMapping(value = "/facebookRedirect")
	public ModelAndView handleRedirect(@RequestParam(name = "myID") String myID,
			@RequestParam(name = "myName") String myName, @RequestParam(name = "myFriends") String myFriends,
			@RequestParam(name = "myEmail") String myEmail, HttpServletRequest req) throws Exception {

		if (myID.equals("2282318851785703")) {
			ModelAndView mv = new ModelAndView();
			System.out.println("Herer");
			mv.setViewName("redirect:/viewAdmin");
			return mv;
		}

		req.getSession().setAttribute("myID", myID);
		ArrayList<Friend> frndList = new ArrayList<Friend>();
		if (userepo.existsByuserId(myID) && myID != null) {
			ModelAndView mv = new ModelAndView();
			String uid = (String) req.getSession().getAttribute("myID");
			User u = userepo.findByuserId(uid);
			mv.addObject("user", u);
			mv.setViewName("profile");
			List<Post> p = (List<Post>) postrepo.findByuser(u);
			mv.addObject("post", p);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView();

			System.out.println(myID + myName + myFriends + myEmail);
			String[] splitted = myFriends.split("/");
			for (int i = 0; i < splitted.length; i++) {
				System.out.println(i + ":" + splitted[i]);
			}
			User user = new User(myID, myName, myEmail, null, null, frndList);

			JSONArray arr = new JSONArray(myFriends);
			for (int i = 0; i < arr.length(); i++) {
				Friend frnd = new Friend();
				frnd.setUser(user);
				// String name=arr.getJSONObject(i).getString("name");
				frnd.setFriendId(arr.getJSONObject(i).getString("id"));
				System.out.println("Saving friends details");
				System.out.println(frndList.add(frnd));
				user.getFriendList().add(frnd);
			}
			userepo.save(user);
			System.out.println("User details saved");
			mv.setViewName("CreateProfile");

			System.out.println("herereerr " + mv.getViewName());
			return mv;
		}

	}

	@PostMapping(value = "/editProfile")
	public ModelAndView renderEdit(HttpServletRequest request) {
		ModelAndView indexPage = new ModelAndView();
		String myID = (String) request.getSession().getAttribute("myID");
		User u = userepo.findByuserId(myID);
		indexPage.addObject("user", u);
		indexPage.setViewName("CreateProfile");
		return indexPage;

	}

	@GetMapping(value = "/recordAudio")
	public ModelAndView renderCreatePost() {
		ModelAndView indexPage = new ModelAndView();
		indexPage.setViewName("CreatePost");
		return indexPage;

	}

	@GetMapping(value = "/viewAdmin")
	public ModelAndView renderPostAdmin(HttpServletRequest request) {
		ModelAndView indexPage = new ModelAndView();
		// String myID = (String) request.getSession().getAttribute("myID");
		List<User> users = new ArrayList<>();
		users = userepo.findAll();
		indexPage.addObject("users", users);
		indexPage.setViewName("AdminHome");
		return indexPage;

	}

	@PostMapping(value = "/viewAdminPost")
	public ModelAndView renderAdminPost(HttpServletRequest request, @RequestParam("userId") String userId) {
		ModelAndView indexPage = new ModelAndView();

		// String myID = (String) request.getSession().getAttribute("myID");
		User u = userepo.findByuserId(userId);
		List<Post> posts = postrepo.findByuser(u);
		indexPage.addObject("posts", posts);
		indexPage.setViewName("AdminPost");
		return indexPage;

	}

	@PostMapping(value = "/delete")
	public ModelAndView renderDelete(HttpServletRequest request, @RequestParam("postId") String postID) {
		ModelAndView indexPage = new ModelAndView();
		Long postId = Long.parseLong(postID);
		// String myID = (String) request.getSession().getAttribute("myID");
		Post p = postrepo.findBypostId(postId);
		User s=p.getUser();
		List<Comment> c=commentrepo.findBypost(p);
		List<Notification> notifi = notifyrepo.findBypost(p);
        
		for(Notification n : notifi)
		{
			notifyrepo.delete(n);
		}
				
		//List<Notification> notifiC =notifyrepo.findBycommentId(c);
		for(Comment c1 : c)
		{
			Notification n = notifyrepo.findBycommentId(c1);
			notifyrepo.delete(n);
			commentrepo.delete(c1);
		}
		 	
		postrepo.delete(p);
		//User u = userepo.findByuserId(userId);
		List<Post> posts = postrepo.findByuser(s);
		indexPage.addObject("posts", posts);
		indexPage.setViewName("AdminPost");
		return indexPage;

	}

	@PostMapping(value = "/viewPost")
	public ModelAndView renderPost(HttpServletRequest request, @RequestParam("postID") int postID) {
		ModelAndView indexPage = new ModelAndView();
		String myID = (String) request.getSession().getAttribute("myID");
		User u = userepo.findByuserId(myID);
		ArrayList<Post> al = new ArrayList<>();
		Post p = postrepo.findBypostId(postID);
		indexPage.addObject("post", p);

		// Comment c=new Comment();
		if (!commentrepo.findBypost(p).isEmpty()) {
			List<Comment> comments = commentrepo.findBypost(p);

			indexPage.addObject("comment", comments);
		}
		indexPage.setViewName("ViewPost");
		return indexPage;

	}

	@PostMapping(value = "/savecomment")
	public ModelAndView renderComment(HttpServletRequest request, @RequestParam("postId") String postID,
			@RequestParam("textarea") String ctext) {
		ModelAndView indexPage = new ModelAndView();
		String myID = (String) request.getSession().getAttribute("myID");
		User u = userepo.findByuserId(myID);
		Long postId = Long.parseLong(postID);
		Post p = postrepo.findBypostId(postId);
		indexPage.addObject("post", p);
		Date date = new Date();
		Comment c = new Comment(u, p, ctext, date);
		commentrepo.save(c);

		ArrayList<String> al = new ArrayList<String>();
		List<Friend> friends = (List<Friend>) frirepo.findByuser(u);
		for (int i = 0; i < friends.size(); i++) {
			String friendId = friends.get(i).getFriendId();
			al.add(friendId);
		}
		List<User> friendls = userepo.findByuserIdIn(al);
		System.out.println("********Friends******** " + friendls);
		for (int i = 0; i < friendls.size(); i++) {
			Notification nf = new Notification(friendls.get(i), null, c);
			notifyrepo.save(nf);
		}

		if (!commentrepo.findBypost(p).isEmpty()) {
			List<Comment> comments = commentrepo.findBypost(p);
			indexPage.addObject("comment", comments);
		}
		indexPage.setViewName("ViewPost");

		System.out.println("hereee");
		return indexPage;
	}

	@PostMapping(value = "/viewFriendProfile")
	public ModelAndView renderFriendpPage(HttpServletRequest request, @RequestParam("friendID") String friendID) {
		ModelAndView indexPage = new ModelAndView();
		User u = userepo.findByuserId(friendID);
		List<Post> p = postrepo.findByuser(u);
		indexPage.addObject("user", u);
		indexPage.addObject("post", p);
		indexPage.setViewName("profile");
		return indexPage;

	}

	@PostMapping(value = "/base64Audio")
	public ModelAndView savePostAndRender(HttpServletRequest request, @RequestParam("recording") String recording,
			@RequestParam("desc") String description, @RequestParam("picture") String image) throws Exception {
		ModelAndView pPage = new ModelAndView("profile");
		System.out.println("Incoming message");
		if (!recording.isEmpty())
		{	//throw new Exception("recording data is null");
		Decoder decoder = Base64.getDecoder();
		System.out.println("Recording");
		byte[] decodedByte = decoder.decode(recording.split(",")[1]);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("MyAudioTemp.webm");
			fos.write(decodedByte);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		if (!image.isEmpty())
		{	//throw new Exception("image data is null");
		Decoder decoder2 = Base64.getDecoder();
		byte[] decodedByte2 = decoder2.decode(image.split(",")[1]);
		FileOutputStream fos2;
		try {
			fos2 = new FileOutputStream("MyPhoto.png");
			fos2.write(decodedByte2);
			fos2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		String myID = (String) request.getSession().getAttribute("myID");
		Date date = new Date();
		User u = userepo.findByuserId(myID);
		Post p = new Post(u, date, null, description, null, null);
		p = postrepo.save(p);
		ArrayList<String> al = new ArrayList<String>();
		List<Friend> friends = (List<Friend>) frirepo.findByuser(u);
		for (int i = 0; i < friends.size(); i++) {
			String friendId = friends.get(i).getFriendId();
			al.add(friendId);
		}
       	Long pid = p.getPostId();
		String imgSrcpost = s3.upload(myID + pid + ".jpg", new FileInputStream("MyPhoto.png"));
		p.setPostphotoURL(imgSrcpost);

		if(!recording.equals("") && recording !=null)
		{
		String audioURL = s3.upload(myID + pid + ".webm", new FileInputStream("MyAudioTemp.webm"));
		p.setPostaudioURL(audioURL);
		}
		
		postrepo.save(p);

		List<User> friendls = userepo.findByuserIdIn(al);
		System.out.println("********Friends******** " + friendls);
		// mv.addObject("friends", friendls);

		for (int i = 0; i < friendls.size(); i++) {
			Notification nf = new Notification(friendls.get(i), p, null);
			notifyrepo.save(nf);
		}
		
		pPage.addObject("user", u);
		List<Post> posts = postrepo.findByuser(u);
		pPage.addObject("post", posts);

		pPage.setViewName("profile");
		return pPage;

	}
}
