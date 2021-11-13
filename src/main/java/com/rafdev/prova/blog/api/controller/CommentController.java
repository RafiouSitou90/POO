package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.comment.CommentDetailsDto;
import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CommentPagination;
import com.rafdev.prova.blog.api.request.CommentRequest;
import com.rafdev.prova.blog.api.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v2/comments")
@Api(tags = "Comments")
@Tags(value = @Tag(name = "Comments", description = "Comments Resources"))
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @Operation(summary = "Create new Comment", description = "Add a single comment resource in the API.")
    public ResponseEntity<CommentDto> saveComment(@RequestBody @Valid CommentRequest commentRequest)
            throws ResourceNotFoundException {

        return new ResponseEntity<>(commentService.saveComment(commentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Comment by Id", description = "Update a single comment resource in the API.")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable("id") Long id,
                                                        @RequestBody @Valid CommentRequest commentRequest)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.updateCommentById(id, commentRequest), HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Get Comments list",
            description = "Get the full list paginated of comments provided by the API."
    )
    public ResponseEntity<Page<CommentDto>> getComments(CommentPagination pagination) {
        return new ResponseEntity<>(commentService.getComments(pagination), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Comment by Id", description = "Get single comment resource provided by the API.")
    public ResponseEntity<CommentDetailsDto> getCommentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Comment by Id", description = "Delete a single comment resource in the API.")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        commentService.deleteCommentById(id);

        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
