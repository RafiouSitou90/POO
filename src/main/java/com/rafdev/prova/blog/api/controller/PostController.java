package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.post.PostDetailsDto;
import com.rafdev.prova.blog.api.dto.post.PostDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.request.PostRequest;
import com.rafdev.prova.blog.api.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v2/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> savePost(@RequestBody @Valid PostRequest postRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(postService.savePost(postRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") Long id,
                                                  @RequestBody @Valid PostRequest postRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(postService.updatePostById(id, postRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailsDto> getPostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
