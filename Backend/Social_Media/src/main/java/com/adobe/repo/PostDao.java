package com.adobe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adobe.model.Post;

public interface PostDao extends JpaRepository<Post, Integer>{

}
