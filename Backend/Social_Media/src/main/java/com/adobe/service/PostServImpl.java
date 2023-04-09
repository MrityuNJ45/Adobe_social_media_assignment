package com.adobe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.dto.PostDTO;
import com.adobe.exception.PostException;
import com.adobe.model.Post;
import com.adobe.model.User;
import com.adobe.repo.PostDao;
import com.adobe.repo.UserDao;

@Service
public class PostServImpl implements PostServ {

	@Autowired
	private PostDao pDao;

	@Autowired
	private UserDao uDao;

	@Override
	public Post registerPost(PostDTO postDto) throws PostException {

		Post p = new Post();
		User u = uDao.findById(postDto.getUserId()).get();
		p.setUser_id(postDto.getUserId());
		p.setContent(postDto.getContent());
		p.setCreated_at(LocalDateTime.now());
		u.getPosts().add(p);
		uDao.save(u);
		return p;
	}

	@Override
	public Post getPostById(Integer id) throws PostException {

		Optional<Post> opt = pDao.findById(id);
		if (opt.isEmpty()) {
			throw new PostException("Invalid Post ID : " + id);
		}

		return opt.get();
	}

	@Override
	public Post updatePostById(Integer id, PostDTO postDTO) throws PostException {

		Optional<Post> opt = pDao.findById(id);
		if (opt.isEmpty()) {
			throw new PostException("Invalid Post ID : " + id);
		}

		Post p = opt.get();
		p.setContent(postDTO.getContent());
		p.setUpdated_at(LocalDateTime.now());
		return pDao.save(p);

	}

	@Override
	public Post deletePostById(Integer id) throws PostException {

		Optional<Post> opt = pDao.findById(id);
		if (opt.isEmpty()) {
			throw new PostException("Invalid Post ID : " + id);
		}
		Post p = opt.get();
		pDao.delete(p);
		return p;
	}

	@Override
	public Post increaseLikeById(Integer id) throws PostException {

		Optional<Post> opt = pDao.findById(id);
		if (opt.isEmpty()) {
			throw new PostException("Invalid Post ID : " + id);
		}

		Post p = opt.get();
		p.setLikes(p.getLikes() + 1);
		return pDao.save(p);

	}

	@Override
	public Post decreaseLikeById(Integer id) throws PostException {

		Optional<Post> opt = pDao.findById(id);
		if (opt.isEmpty()) {
			throw new PostException("Invalid Post ID : " + id);
		}

		Post p = opt.get();
		if (p.getLikes() == 0) {
			throw new PostException("This post contains 0 likes, can't decrease further...");
		}

		p.setLikes(p.getLikes() - 1);

		return pDao.save(p);
	}

	@Override
	public List<Post> getAllPosts() throws PostException {

		List<Post> list = pDao.findAll();
		if (list.size() == 0) {
			throw new PostException("No posts found...");
		}

		return list;
	}

	@Override
	public List<Post> getTop5LikePosts() throws PostException {
		List<Post> list = pDao.findAll();
		if (list.size() == 0) {
			throw new PostException("No posts found...");
		}
		
		List<Post> list2 = list.stream().sorted((a,b) -> b.getLikes() - a.getLikes()).limit(5).toList();
		
		return list2;
	}

}
