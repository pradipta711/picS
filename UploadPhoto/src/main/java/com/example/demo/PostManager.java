package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UploadToS3;

@Controller
public class PostManager {
	@Autowired
	UploadToS3 s3;
/*
	@GetMapping(value="/recordAudio")
	public ModelAndView renderPage() {
		ModelAndView indexPage = new ModelAndView();
		indexPage.setViewName("audioRecord");
		return indexPage;

	}

	@PostMapping(value="/base64Audio")
	public ModelAndView saveAudioAndRender(HttpServletRequest request,@RequestParam("recording")String recording,
			@RequestParam("desc")String description,
			@RequestParam("file") MultipartFile image) throws Exception
	{
		ModelAndView successPage=new ModelAndView("ViewPost");
		System.out.println("Incoming message");
		if(recording.isEmpty()) throw new Exception("recording data is null");
		Decoder decoder=Base64.getDecoder();
		System.out.println("Recording");
		byte[] decodedByte=decoder.decode(recording.split(",")[1]);
		FileOutputStream fos;
		try {
			fos=new FileOutputStream("MyAudioTemp.webm");
			fos.write(decodedByte);
			fos.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		String imgSrcp=s3.upload(image.getOriginalFilename(), image.getInputStream());
		String myID=(String) request.getSession().getAttribute("myID");
		String postID="1";
		String audioURL=s3.upload(myID+postID+".webm",new FileInputStream("MyAudioTemp.webm"));
		successPage.addObject("audioURL",audioURL);
		return successPage;
		
	}*/
	
	
	
	
	
}
