package com.adobe.service;

import java.util.List;

import com.adobe.dto.PostDTO;
import com.adobe.exception.PostException;
import com.adobe.model.Post;

public interface PostServ {

	
	public Post registerPost(PostDTO postDto) throws PostException;
	
	public Post getPostById(Integer id) throws PostException;
	
	public Post updatePostById(Integer id, PostDTO postDTO) throws PostException;
	
	public Post deletePostById(Integer id) throws PostException;
	
	public Post increaseLikeById(Integer id) throws PostException;
	
	public Post decreaseLikeById(Integer id) throws PostException;
	
	public List<Post> getAllPosts() throws PostException;
	
	public List<Post> getTop5LikePosts() throws PostException;
	
	
}
