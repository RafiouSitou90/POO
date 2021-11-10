package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.comment.CommentDetailsDto;
import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CommentPagination;
import com.rafdev.prova.blog.api.request.CommentRequest;
import com.rafdev.prova.blog.api.service.CommentService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v2/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> saveComment(@RequestBody @Valid CommentRequest commentRequest)
            throws ResourceNotFoundException {

        return new ResponseEntity<>(commentService.saveComment(commentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable("id") Long id,
                                                        @RequestBody @Valid CommentRequest commentRequest)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.updateCommentById(id, commentRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CommentDto>> getComments(CommentPagination pagination) {
        return new ResponseEntity<>(commentService.getComments(pagination), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDetailsDto> getCommentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        commentService.deleteCommentById(id);

        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
