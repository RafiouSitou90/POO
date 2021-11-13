package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.comment.CommentDetailsDto;
import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CommentPagination;
import com.rafdev.prova.blog.api.request.CommentRequest;

import org.springframework.data.domain.Page;

public interface CommentService {

    CommentDto saveComment(CommentRequest commentRequest) throws ResourceNotFoundException;

    CommentDto updateCommentById(Long id, CommentRequest commentRequest) throws ResourceNotFoundException;

    Page<CommentDto> getComments(CommentPagination pagination);

    CommentDetailsDto getCommentById(Long id) throws ResourceNotFoundException;

    void deleteCommentById(Long id) throws ResourceNotFoundException;
}
