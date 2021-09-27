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

        if (postRepository.existsByTitle(postRequest.getTitle())) {
            throw new ResourceAlreadyExistsException(resourceName, "Title", postRequest.getTitle());
        }

        User user = userRepository.findById(postRequest.getUserId());

        if (user == null) {
            throw new ResourceNotFoundException("User", "Id", postRequest.getUserId());
        }

        Category category = null;
        if (postRequest.getCategoryId() != null) {
            category = categoryRepository.findById(postRequest.getCategoryId());

            if (category == null) {
                throw new ResourceNotFoundException("Category", "Id", postRequest.getCategoryId());
            }
        }

        Post post = new Post(idCounter.incrementAndGet(), postRequest.getTitle(), postRequest.getContent(),
                postRequest.getImageUrl(), user, category, postRequest.getPublishedAt());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);

        Post postCreated = postRepository.save(post);

        return new PostDto(postCreated);
    }

    @Override
    public PostDto updatePostById(Long id, PostRequest postRequest) {
        Post postFound = postRepository.findById(id);

        if (postFound == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        Post postFoundByTitle = postRepository.findByTitle(postRequest.getTitle());
        if (postFoundByTitle != null && !Objects.equals(postFound.getTitle(), postFoundByTitle.getTitle())) {
            throw new ResourceAlreadyExistsException(resourceName, "Title", postRequest.getTitle());
        }

        Category category = postFound.getCategory();
        if (postRequest.getCategoryId() != null) {
            category = categoryRepository.findById(postRequest.getCategoryId());

            if (category == null) {
                throw new ResourceNotFoundException("Category", "Id", postRequest.getCategoryId());
            }
        }

        postFound.setTitle(postRequest.getTitle());
        postFound.setContent(postRequest.getContent());
        postFound.setImageUrl(postRequest.getImageUrl());
        postFound.setCategory(category);
        postFound.setUpdatedAt(LocalDateTime.now());

        Post postUpdated = postRepository.update(postFound);

        return new PostDto(postUpdated);
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
        Post post = postRepository.findById(id);

        if (post == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        return setPostDtoWithComment(post);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id);

        if (post == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        postRepository.delete(post.getId());
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
}