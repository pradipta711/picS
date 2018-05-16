package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Notification {

	public Notification()
	{
		
	}
	
	public Notification(User user, Post post, Comment commentId) {
		super();
		this.user = user;
		this.post = post;
		this.commentId = commentId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long notificationId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="postId")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name="commentId")
	private Comment commentId;
	
	
	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment getCommentId() {
		return commentId;
	}

	public void setCommentId(Comment commentId) {
		this.commentId = commentId;
	}

	
		
	
}
