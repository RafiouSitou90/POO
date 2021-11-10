package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.dto.post.PostDetailsDto;
import com.rafdev.prova.blog.api.dto.post.PostDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CommentPagination;
import com.rafdev.prova.blog.api.pagination.PostPagination;
import com.rafdev.prova.blog.api.request.PostRequest;
import com.rafdev.prova.blog.api.service.PostService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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

    @PatchMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") Long id,
                                                   @RequestBody Map<String, Object> postRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(postService.updatePostById(id, postRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> getPosts(PostPagination pagination) {
        return new ResponseEntity<>(postService.getPosts(pagination), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailsDto> getPostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PatchMapping("/publish/{id}")
    public ResponseEntity<PostDto> publishPostById(@PathVariable("id") Long id,
                                                   @RequestBody Map<String, Object> postRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.publishPostById(id, postRequest), HttpStatus.OK);
    }

    @PatchMapping("/archive/{id}")
    public ResponseEntity<PostDto> archivePostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.archivePostById(id), HttpStatus.OK);
    }

    @PatchMapping("/draft/{id}")
    public ResponseEntity<PostDto> draftPostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.draftPostById(id), HttpStatus.OK);
    }

    @GetMapping("/published")
    public ResponseEntity<Page<PostDto>> getPostsPublished(PostPagination pagination) {
        return new ResponseEntity<>(postService.getPostsPublished(pagination), HttpStatus.OK);
    }

    @GetMapping("/archived")
    public ResponseEntity<Page<PostDto>> getPostsArchived(PostPagination pagination) {
        return new ResponseEntity<>(postService.getPostsArchived(pagination), HttpStatus.OK);
    }

    @GetMapping("/drafted")
    public ResponseEntity<Page<PostDto>> getPostsDrafted(PostPagination pagination) {
        return new ResponseEntity<>(postService.getPostsDrafted(pagination), HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<Page<PostDto>> getPostsByTag(String tag, PostPagination pagination) {
        return new ResponseEntity<>(postService.getPostsByTag(tag, pagination), HttpStatus.OK);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Page<CommentDto>> getCommentsByPostId(@PathVariable("id") Long id,
                                                                CommentPagination pagination)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.getCommentsByPostId(id, pagination), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
