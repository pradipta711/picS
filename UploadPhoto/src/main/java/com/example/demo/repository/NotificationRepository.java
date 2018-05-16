package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Comment;
import com.example.demo.model.Notification;
import com.example.demo.model.Post;
import com.example.demo.model.User;

public interface NotificationRepository  extends CrudRepository<Notification,Long> {

	
	public List<Notification> findByuser(User user);
	 public List<Notification> findBypost(Post p);
	 public Notification findBycommentId(Comment c);
     
}
