package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.builder.CommentBuilder;
import com.rafdev.prova.blog.api.dto.comment.CommentDetailsDto;
import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.entity.Comment;
import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CommentPagination;
import com.rafdev.prova.blog.api.repository.CommentRepository;
import com.rafdev.prova.blog.api.repository.PostRepository;
import com.rafdev.prova.blog.api.repository.UserRepository;
import com.rafdev.prova.blog.api.request.CommentRequest;
import com.rafdev.prova.blog.api.service.CommentService;
import com.rafdev.prova.blog.api.util.UtilityFunctions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        UtilityFunctions.denyAccessUnlessGranted(
                commentFound.getUser(), "Access denied! You don't have to access to update this Comment"
        );

        commentFound.setContent(commentRequest.getContent());

        return new CommentDto(commentRepository.save(commentFound));
    }

    @Override
    public Page<CommentDto> getComments(CommentPagination pagination) {
        Pageable pageable = UtilityFunctions.getPageable(pagination);
        Page<Comment> comments = commentRepository.findAll(pageable);

        return comments.map(CommentDto::new);
    }

    @Override
    public CommentDetailsDto getCommentById(Long id) throws ResourceNotFoundException {
        return new CommentDetailsDto(getCommentOrThrowException(id));
    }

    @Override
    public void deleteCommentById(Long id) throws ResourceNotFoundException {
        Comment comment = getCommentOrThrowException(id);
        UtilityFunctions.denyAccessUnlessGranted(
                comment.getUser(), "Access denied! You don't have to access to delete this Comment"
        );

        commentRepository.delete(comment);
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
