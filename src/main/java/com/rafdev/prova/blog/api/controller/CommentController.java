package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.CommentDto;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.request.CommentRequest;
import com.rafdev.prova.blog.api.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
//@PreAuthorize("hasRole('USER')")
//@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPER_ADMIN')")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
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

    @GetMapping("")
    public ResponseEntity<List<CommentDto>> getComments() {
        return new ResponseEntity<>(commentService.getComments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        commentService.deleteCommentById(id);

        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
