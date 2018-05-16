package com.example.demo.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long postId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	private Date timestamp;
	private String postphotoURL;
	private String text;
	private String postaudioURL;

	@OneToMany(mappedBy="post", cascade= {CascadeType.ALL})
	private List<Comment> commentsList;

	public Post() {
		
	}
	public Post(User user, Date timestamp, String postphotoURL, String text, String postaudioURL,
			List<Comment> commentsList) {
		super();
		this.user = user;
		this.timestamp = timestamp;
		this.postphotoURL = postphotoURL;
		this.text = text;
		this.postaudioURL = postaudioURL;
		this.commentsList = commentsList;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
    
	public List<Comment> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	public long getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getPostphotoURL() {
		return postphotoURL;
	}
	public void setPostphotoURL(String postphotoURL) {
		this.postphotoURL = postphotoURL;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPostaudioURL() {
		return postaudioURL;
	}
	public void setPostaudioURL(String postaudioURL) {
		this.postaudioURL = postaudioURL;
	}
	/*@Override
	public String toString() {
		return "Post [postId=" + postId + ", user=" + user + ", timestamp=" + timestamp + ", postphotoURL="
				+ postphotoURL + ", text=" + text + ", postaudioURL=" + postaudioURL + ", commentsList=" + commentsList
				+ "]";
	}*/

	
	
	
}
