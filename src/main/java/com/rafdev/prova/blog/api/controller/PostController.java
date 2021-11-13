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

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v2/posts")
@Api(tags = "Posts")
@Tags(value = @Tag(name = "Posts", description = "Posts Resources"))
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @Operation(summary = "Create new Post", description = "Add a single post resource in the API.")
    public ResponseEntity<PostDto> savePost(@RequestBody @Valid PostRequest postRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(postService.savePost(postRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Post by Id", description = "Update a single post resource in the API.")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") Long id,
                                                  @RequestBody @Valid PostRequest postRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(postService.updatePostById(id, postRequest), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Update partially Post by Id",
            description = "Update partially a single post resource in the API."
    )
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") Long id,
                                                   @RequestBody Map<String, Object> postRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(postService.updatePostById(id, postRequest), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get Posts list", description = "Get the full paginated list of posts provided by the API.")
    public ResponseEntity<Page<PostDto>> getPosts(PostPagination pagination) {
        return new ResponseEntity<>(postService.getPosts(pagination), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Post by Id", description = "Get single post resource provided by the API.")
    public ResponseEntity<PostDetailsDto> getPostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PatchMapping("/publish/{id}")
    @Operation(summary = "Publish Post by Id", description = "Publish a single post resource in the API.")
    public ResponseEntity<PostDto> publishPostById(@PathVariable("id") Long id,
                                                   @RequestBody Map<String, Object> postRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.publishPostById(id, postRequest), HttpStatus.OK);
    }

    @PatchMapping("/archive/{id}")
    @Operation(summary = "Archive Post by Id", description = "Archive a single post resource in the API.")
    public ResponseEntity<PostDto> archivePostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.archivePostById(id), HttpStatus.OK);
    }

    @PatchMapping("/draft/{id}")
    @Operation(summary = "Draft Post by Id", description = "Draft a single post resource in the API.")
    public ResponseEntity<PostDto> draftPostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.draftPostById(id), HttpStatus.OK);
    }

    @GetMapping("/published")
    @Operation(
            summary = "Get Posts Published",
            description = "Get the full paginated list of published posts provided by the API."
    )
    public ResponseEntity<Page<PostDto>> getPostsPublished(PostPagination pagination) {
        return new ResponseEntity<>(postService.getPostsPublished(pagination), HttpStatus.OK);
    }

    @GetMapping("/archived")
    @Operation(
            summary = "Get Posts Archived",
            description = "Get the full paginated list of archived posts provided by the API."
    )
    public ResponseEntity<Page<PostDto>> getPostsArchived(PostPagination pagination) {
        return new ResponseEntity<>(postService.getPostsArchived(pagination), HttpStatus.OK);
    }

    @GetMapping("/drafted")
    @Operation(
            summary = "Get Posts Drafted",
            description = "Get the full paginated list of drafted posts provided by the API."
    )
    public ResponseEntity<Page<PostDto>> getPostsDrafted(PostPagination pagination) {
        return new ResponseEntity<>(postService.getPostsDrafted(pagination), HttpStatus.OK);
    }

    @GetMapping("/tags")
    @Operation(
            summary = "Get Posts by tag",
            description = "Get the full paginated list of posts resource by tag name provided by the API."
    )
    public ResponseEntity<Page<PostDto>> getPostsByTag(String tag, PostPagination pagination) {
        return new ResponseEntity<>(postService.getPostsByTag(tag, pagination), HttpStatus.OK);
    }

    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Get Post Comments by Post Id",
            description = "Get the full paginated list of post comments resource by id provided by the API."
    )
    public ResponseEntity<Page<CommentDto>> getCommentsByPostId(@PathVariable("id") Long id,
                                                                CommentPagination pagination)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(postService.getCommentsByPostId(id, pagination), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Post by Id", description = "Delete a single post resource in the API.")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
