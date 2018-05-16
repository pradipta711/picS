package com.example.demo.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id 
	private String userId;
	private String name;
	private String email;
	private String profilephoto;
	private String description;
	
	@OneToMany(mappedBy="user", cascade= {CascadeType.ALL})
	private List<Friend> friendList;
	
	@OneToMany(mappedBy="user", cascade= {CascadeType.ALL})
	private List<Post> postList;
	
	@OneToMany(mappedBy="user", cascade= {CascadeType.ALL})
    private List<Comment> commentList;
	
	public User(String userId, String name, String email, String profilephoto, String description,
			List<Friend> friendList) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.profilephoto = profilephoto;
		this.description = description;
		this.friendList = friendList;
	}
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfilephoto() {
		return profilephoto;
	}
	public void setProfilephoto(String profilephoto) {
		this.profilephoto = profilephoto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Friend> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}
   public List<Post> getPostList() {
		return postList;
	}
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	
	/*@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", profilephoto=" + profilephoto
				+ ", description=" + description + ", friendList=" + friendList + "]";
	}*/
	
	
}
