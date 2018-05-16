package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Post;
import com.example.demo.model.User;

public interface PostRepository extends CrudRepository<Post,Integer>{

	public List<Post> findByuser(User userobj);
	//public List<Post> findByuserId(String userId);
    public Post findBypostId(long id);
 //   public List<Post> findByuserId(User userobj);
	//public boolean existsByuserId(String id);*/
    
}
