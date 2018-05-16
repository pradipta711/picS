package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Post;
import com.example.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    public User findByuserId(String id);
    public boolean existsByuserId(String id);
    public List<User> findByuserIdIn(ArrayList<String> al);
    public List<User> findAll();
}
