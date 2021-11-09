package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.CommentDto;
import com.rafdev.prova.blog.api.dto.PostDto;
import com.rafdev.prova.blog.api.entity.Category;
import com.rafdev.prova.blog.api.entity.Comment;
import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.repository.CategoryRepository;
import com.rafdev.prova.blog.api.repository.CommentRepository;
import com.rafdev.prova.blog.api.repository.PostRepository;
import com.rafdev.prova.blog.api.repository.UserRepository;
import com.rafdev.prova.blog.api.request.PostRequest;
import com.rafdev.prova.blog.api.service.PostService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PostServiceImpl implements PostService {

    private final String resourceName = "Post";
    private final AtomicLong idCounter = new AtomicLong(100);
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository,
                           UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public PostDto savePost(PostRequest postRequest) {

        if (postRepository.existsByTitleIgnoreCase(postRequest.getTitle())) {
            throw new ResourceAlreadyExistsException(resourceName, "Title", postRequest.getTitle());
        }

        User user = getUserOrThrowException(postRequest.getUserId());

        Category category = getCategoryOrThrowException(postRequest.getCategoryId());

        Post post = new Post(postRequest.getTitle(), postRequest.getContent(),
                postRequest.getImageUrl(), user, category, postRequest.getPublishedAt());

        return new PostDto(postRepository.save(post));
    }

    @Override
    public PostDto updatePostById(Long id, PostRequest postRequest) {
        Post postFound = getPostOrThrowException(id);

        if(!Objects.equals(postFound.getTitle().toLowerCase(), postRequest.getTitle().toLowerCase())) {
            if (postRepository.existsByTitleIgnoreCase(postRequest.getTitle())) {
                throw new ResourceAlreadyExistsException(resourceName, "Title", postRequest.getTitle());
            }
        }

        Category category = getCategoryOrThrowException(postRequest.getCategoryId());

        postFound.setTitle(postRequest.getTitle());
        postFound.setContent(postRequest.getContent());
        postFound.setImageUrl(postRequest.getImageUrl());
        postFound.setCategory(category);

        return new PostDto(postRepository.save(postFound));
    }

    @Override
    public List<PostDto> getPosts() {
        List<PostDto> postsDto = new ArrayList<>();
        List<Post> posts = postRepository.findAll();

        for (Post post: posts) {
            postsDto.add(new PostDto(post));
        }

        return postsDto;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = getPostOrThrowException(id);

        return setPostDtoWithComment(post);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = getPostOrThrowException(id);

        postRepository.delete(post);
    }

    private PostDto setPostDtoWithComment(Post post) {
        List<Comment> commentsList = commentRepository.findAllByPostId(post.getId());
        List<CommentDto> commentsListDto = new ArrayList<>();

        for (Comment comment: commentsList) {
            CommentDto commentDto = new CommentDto(comment);

            commentsListDto.add(commentDto);
        }

        return new PostDto(post, commentsListDto);
    }
    private Post getPostOrThrowException(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));
    }

    private User getUserOrThrowException(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    private Category getCategoryOrThrowException(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
    }
}