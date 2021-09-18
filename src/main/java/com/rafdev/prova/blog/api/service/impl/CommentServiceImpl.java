package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.CommentDto;
import com.rafdev.prova.blog.api.entity.Comment;
import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.repository.CommentRepository;
import com.rafdev.prova.blog.api.repository.PostRepository;
import com.rafdev.prova.blog.api.repository.UserRepository;
import com.rafdev.prova.blog.api.request.CommentRequest;
import com.rafdev.prova.blog.api.service.CommentService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CommentServiceImpl implements CommentService {

    private final String resourceName = "Comment";
    private final AtomicLong idCounter = new AtomicLong(100);
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository,
                              PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto saveComment(CommentRequest commentRequest) {

        User user = userRepository.findById(commentRequest.getUserId());
        if (user == null) {
            throw new ResourceNotFoundException("User", "Id", commentRequest.getUserId());
        }

        Post post = postRepository.findById(commentRequest.getPostId());
        if (post == null) {
            throw new ResourceNotFoundException("Post", "Id", commentRequest.getUserId());
        }

        Comment comment = new Comment(idCounter.incrementAndGet(), commentRequest.getContent(), user, post,
                LocalDateTime.now());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(null);

        Comment commentCreated = commentRepository.save(comment);

        return setCommentDto(commentCreated);
    }

    @Override
    public CommentDto updateCommentById(Long id, CommentRequest commentRequest) {
        Comment commentFound = commentRepository.findById(id);

        if (commentFound == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        commentFound.setContent(commentRequest.getContent());
        commentFound.setUpdatedAt(LocalDateTime.now());

        Comment commentUpdated = commentRepository.update(commentFound);

        return setCommentDto(commentUpdated);
    }

    @Override
    public List<CommentDto> getComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentsDto = new ArrayList<>();

        for (Comment comment: comments) {
            commentsDto.add(setCommentDto(comment));
        }

        return commentsDto;
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id);

        if (comment == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        return setCommentDto(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        Comment comment = commentRepository.findById(id);

        if (comment == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        commentRepository.delete(comment.getId());
    }

    private CommentDto setCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent(), comment.getUser(), comment.getPost(),
                comment.getPublishedAt());
    }
}
