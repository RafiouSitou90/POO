package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.CommentDto;
import com.rafdev.prova.blog.api.request.CommentRequest;

import java.util.List;

public interface CommentService {

    CommentDto saveComment(CommentRequest commentRequest);

    CommentDto updateCommentById(Long id, CommentRequest commentRequest);

    List<CommentDto> getComments();

    CommentDto getCommentById(Long id);

    void deleteCommentById(Long id);
}
