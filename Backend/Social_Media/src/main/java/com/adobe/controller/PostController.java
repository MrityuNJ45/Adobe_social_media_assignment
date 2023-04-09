package com.adobe.controller;

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

}
