package com.hanu.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hanu.webapp.exception.ResourceNotFoundException;
import com.hanu.webapp.model.Post;
import com.hanu.webapp.repository.PostRepository;

@RestController
@CrossOrigin
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@CrossOrigin
	@GetMapping("/api/posts")
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	@GetMapping("/api/post/{id}")
	public Post byId(@PathVariable Long id) {
		return postRepository.findById(id).get();
	}
	
	
	@PostMapping("/api/post")
	public Post createPost(@Valid @RequestBody Post post) {
		return postRepository.save(post);
	}
	
	@PutMapping("/api/post/{postId}")
	public Post updatePost(@PathVariable Long postId,@Valid  @RequestBody Post postRequest) {
		return postRepository.findById(postId)
								.map(post ->{
									post.setTitle(postRequest.getTitle());
									post.setContent(postRequest.getContent());
									post.setDescription(postRequest.getContent());
									return postRepository.save(post);
								}).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}
	
	@DeleteMapping("/api/post/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Long postId){
		return postRepository.findById(postId)
							.map(post ->{
								postRepository.delete(post);
								return ResponseEntity.ok().build();
							}).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}
	
	
}
