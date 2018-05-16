package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Comment;
import com.example.demo.model.Friend;
import com.example.demo.model.Post;
import com.example.demo.model.User;

public interface CommentRepository extends CrudRepository<Comment,Integer>{

	public List<Comment> findBypost(Post postobj);
	
//	public List<Comment> findBy(Post post);
}
