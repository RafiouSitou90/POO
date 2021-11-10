package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.builder.PostBuilder;
import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.dto.post.PostDetailsDto;
import com.rafdev.prova.blog.api.dto.post.PostDto;
import com.rafdev.prova.blog.api.entity.*;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.PostPagination;
import com.rafdev.prova.blog.api.repository.*;
import com.rafdev.prova.blog.api.request.PostRequest;
import com.rafdev.prova.blog.api.service.PostService;

import com.rafdev.prova.blog.api.service.TagService;
import com.rafdev.prova.blog.api.util.UtilityFunctions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    private final String resourceName = "Post";
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository,
                           UserRepository userRepository, CommentRepository commentRepository, TagRepository tagRepository, TagService tagService) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.tagRepository = tagRepository;
        this.tagService = tagService;
    }

    @Override
    public PostDto savePost(PostRequest postRequest) throws ResourceNotFoundException, ResourceAlreadyExistsException {

        if (postRepository.existsByTitleIgnoreCase(postRequest.getTitle())) {
            throw new ResourceAlreadyExistsException(resourceName, "Title", postRequest.getTitle());
        }

        User user = getUserOrThrowException(postRequest.getUserId());
        Category category = getCategoryOrThrowException(postRequest.getCategoryId());
        Set<Tag> tags = getPostTags(postRequest.getTags());


        Post post = new PostBuilder()
                .withTitle(postRequest.getTitle())
                .withContent(postRequest.getContent())
                .withImageUrl(postRequest.getImageUrl())
                .withPublishedAt(postRequest.getPublishedAt())
                .withUser(user)
                .withCategory(category)
                .withTags(tags)
                .build();

        return new PostDto(postRepository.save(post));
    }

    @Override
    public PostDto updatePostById(Long id, PostRequest postRequest) throws ResourceNotFoundException,
            ResourceAlreadyExistsException {

        Post postFound = getPostOrThrowException(id);

        if (!Objects.equals(postFound.getTitle().toLowerCase(), postRequest.getTitle().toLowerCase())) {
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
    public Page<PostDto> getPosts(PostPagination pagination) {
        Pageable pageable = UtilityFunctions.getPageable(pagination);
        Page<Post> posts = postRepository.findAll(pageable);

        return posts.map(PostDto::new);
    }

    @Override
    public PostDetailsDto getPostById(Long id) throws ResourceNotFoundException {
        return getPostDtoWithComment(getPostOrThrowException(id));
    }

    @Override
    public void deletePostById(Long id) throws ResourceNotFoundException {
        postRepository.delete(getPostOrThrowException(id));
    }

    private PostDetailsDto getPostDtoWithComment(Post post) {

        List<Comment> comments = commentRepository.findAllByPostId(post.getId());
        Set<CommentDto> commentsDto = new HashSet<>();

        comments.forEach(comment -> commentsDto.add(new CommentDto(comment)));

        return new PostDetailsDto(post, commentsDto);
    }

    private Post getPostOrThrowException(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));
    }

    private User getUserOrThrowException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    private Category getCategoryOrThrowException(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
    }

    private Set<Tag> getPostTags(Set<String> strTags) {

        Set<Tag> tags = new HashSet<>();

        if (strTags != null && !strTags.isEmpty()) {
            strTags.forEach(tag -> tags.add(getTagByName(tag)));
        }

        return tags;
    }

    private Tag getTagByName(String name) {
        return tagService.getOrCreateByName(name);
    }
}