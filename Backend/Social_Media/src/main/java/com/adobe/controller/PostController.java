package com.adobe.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.dto.PostDTO;
import com.adobe.exception.PostException;
import com.adobe.model.Post;
import com.adobe.service.PostServ;

@CrossOrigin
@RestController
public class PostController {

	@Autowired
	private PostServ pServ;

	@PostMapping("/posts")
	public ResponseEntity<Post> registerPost(@RequestBody PostDTO postDTO) throws PostException {

		Post p = pServ.registerPost(postDTO);
		return new ResponseEntity<Post>(p, HttpStatus.CREATED);

	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id) throws PostException {

		Post p = pServ.getPostById(id);
		return new ResponseEntity<Post>(p, HttpStatus.OK);

	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> updatePostById(@PathVariable("id") Integer id, @RequestBody PostDTO postDTO)
			throws PostException {

		Post p = pServ.updatePostById(id, postDTO);
		return new ResponseEntity<Post>(p, HttpStatus.OK);

	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Post> deletePostById(@PathVariable("id") Integer id) throws PostException {

		Post p = pServ.deletePostById(id);
		return new ResponseEntity<Post>(p, HttpStatus.OK);

	}
	
	@GetMapping("/analytics/posts")
	public ResponseEntity<List<Post>> getAllPost() throws PostException{
		
		List<Post> list = pServ.getAllPosts();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/analytics/posts/top-liked")
	public ResponseEntity<List<Post>> top5Posts() throws PostException{
		
		List<Post> list = pServ.getTop5LikePosts();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@PostMapping("/posts/{id}/like")
	public ResponseEntity<Post> likePost(@PathVariable("id") Integer id) throws PostException{
		Post p = pServ.increaseLikeById(id);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@PostMapping("/posts/{id}/unlike")
	public ResponseEntity<Post> unlikePost(@PathVariable("id") Integer id) throws PostException{
		Post p = pServ.decreaseLikeById(id);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

}
