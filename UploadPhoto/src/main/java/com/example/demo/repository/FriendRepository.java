package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CKey;
import com.example.demo.model.Friend;
import com.example.demo.model.User;

@Repository 
public interface FriendRepository extends CrudRepository<Friend,CKey> {

	public List<Friend> findByuser(User userobj);
	
}
