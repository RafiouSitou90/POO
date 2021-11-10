package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.builder.CommentBuilder;
import com.rafdev.prova.blog.api.dto.comment.CommentDetailsDto;
import com.rafdev.prova.blog.api.dto.comment.CommentDto;
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

@Service
public class CommentServiceImpl implements CommentService {

    private final String resourceName = "Comment";
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
    public CommentDto saveComment(CommentRequest commentRequest) throws ResourceNotFoundException {

        User user = getUserOrThrowException(commentRequest.getUserId());
        Post post = getPostOrThrowException(commentRequest.getPostId());

        Comment comment = new CommentBuilder()
                .withContent(commentRequest.getContent())
                .withUser(user)
                .withPost(post)
                .withPublishedAt(LocalDateTime.now())
                .build();

        return new CommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto updateCommentById(Long id, CommentRequest commentRequest) throws ResourceNotFoundException {

        Comment commentFound = getCommentOrThrowException(id);
        commentFound.setContent(commentRequest.getContent());

        return new CommentDto(commentRepository.save(commentFound));
    }

    @Override
    public List<CommentDto> getComments() {

        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentsDto = new ArrayList<>();

        comments.forEach(comment -> commentsDto.add(new CommentDto(comment)));

        return commentsDto;
    }

    @Override
    public CommentDetailsDto getCommentById(Long id) throws ResourceNotFoundException {
        return new CommentDetailsDto(getCommentOrThrowException(id));
    }

    @Override
    public void deleteCommentById(Long id) throws ResourceNotFoundException {
        commentRepository.delete(getCommentOrThrowException(id));
    }

    private Comment getCommentOrThrowException(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));
    }

    private Post getPostOrThrowException(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
    }

    private User getUserOrThrowException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }
}
