package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.builder.PostBuilder;
import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.dto.post.PostDetailsDto;
import com.rafdev.prova.blog.api.dto.post.PostDto;
import com.rafdev.prova.blog.api.entity.*;
import com.rafdev.prova.blog.api.enums.EPostStatus;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceBadRequestException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CommentPagination;
import com.rafdev.prova.blog.api.pagination.PostPagination;
import com.rafdev.prova.blog.api.repository.*;
import com.rafdev.prova.blog.api.request.PostRequest;
import com.rafdev.prova.blog.api.service.PostService;

import com.rafdev.prova.blog.api.service.TagService;
import com.rafdev.prova.blog.api.util.UtilityFunctions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
                           UserRepository userRepository, CommentRepository commentRepository,
                           TagRepository tagRepository, TagService tagService) {
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
        UtilityFunctions.denyAccessUnlessGranted(
                postFound.getUser(),
                "Access denied! You don't have access to update this Post"
        );

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
    public PostDto updatePostById(Long id, Map<String, Object> postRequest) throws ResourceNotFoundException, ResourceAlreadyExistsException {

        Post postFound = getPostOrThrowException(id);
        UtilityFunctions.denyAccessUnlessGranted(
                postFound.getUser(),
                "Access denied! You don't have access to update this Post"
        );

        Post postToPatch = new PostBuilder()
                .withTitle(postFound.getTitle())
                .withContent(postFound.getContent())
                .withUser(postFound.getUser())
                .withCategory(postFound.getCategory())
                .withImageUrl(postFound.getImageUrl())
                .withPublishedAt(postFound.getPublishedAt())
                .withTags(postFound.getTags())
                .build();
        postToPatch.setId(postFound.getId());
        postToPatch.setSlug(postFound.getSlug());
        postToPatch.setCreatedAt(postFound.getCreatedAt());
        postToPatch.setUpdatedAt(postFound.getUpdatedAt());

        if (!postRequest.isEmpty()) {
            postRequest.forEach((field, value) -> {
                switch (field) {
                    case "title" -> {
                        if (!Objects.equals(postFound.getTitle().toLowerCase(), value.toString().toLowerCase())) {
                            if (postRepository.existsByTitleIgnoreCase((String) value)) {
                                throw new ResourceAlreadyExistsException(resourceName, "Title", value);
                            }
                        }

                        postToPatch.setTitle((String) value);
                    }
                    case "content" -> postToPatch.setContent((String) value);
                    case "imageUrl" -> postToPatch.setImageUrl((String) value);
                    case "categoryId" -> {
                        Integer catId = (Integer) value;
                        Category category = getCategoryOrThrowException(catId.longValue());
                        postToPatch.setCategory(category);
                    }
                    case "tags" -> {
                        Set<String> strTags = new HashSet<>((List<String>) value);
                        postToPatch.setTags(getPostTags(strTags));
                    }
                    case "publishedAt" -> {
                        try {
                            LocalDateTime publishedAt = LocalDateTime.parse((String) value);
                            postToPatch.setPublishedAt(publishedAt);
                        } catch (DateTimeParseException ex) {
                            throw new ResourceBadRequestException("Invalid datetime. Example: \"2021-12-31T23:59:59\"");
                        }
                    }
                }
            });
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Post>> violations = validator.validate(postToPatch);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return new PostDto(postRepository.save(postToPatch));
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
    public PostDto publishPostById(Long id, Map<String, Object> postRequest) {
        Post postFound = getPostOrThrowException(id);
        UtilityFunctions.denyAccessUnlessGranted(
                postFound.getUser(),
                "Access denied! You don't have access to publish this Post"
        );

        postFound.setPublishedAt(LocalDateTime.now());
        postFound.setStatus(EPostStatus.PUBLISHED);

        if (!postRequest.isEmpty()) {
            postRequest.forEach((field, value) -> {
                if ("publishedAt".equals(field)) {
                    postFound.setPublishedAt(LocalDateTime.parse((String) value));
                }
            });
        }

        return new PostDto(postRepository.save(postFound));
    }

    @Override
    public PostDto archivePostById(Long id) throws ResourceNotFoundException {
        Post postFound = getPostOrThrowException(id);
        UtilityFunctions.denyAccessUnlessGranted(
                postFound.getUser(),
                "Access denied! You don't have access to archive this Post"
        );

        postFound.setPublishedAt(null);
        postFound.setStatus(EPostStatus.ARCHIVED);

        return new PostDto(postRepository.save(postFound));
    }

    @Override
    public PostDto draftPostById(Long id) throws ResourceNotFoundException {
        Post postFound = getPostOrThrowException(id);
        UtilityFunctions.denyAccessUnlessGranted(
                postFound.getUser(),
                "Access denied! You don't have access to draft this Post"
        );

        postFound.setPublishedAt(null);
        postFound.setStatus(EPostStatus.DRAFT);

        return new PostDto(postRepository.save(postFound));
    }

    @Override
    public Page<PostDto> getPostsPublished(PostPagination pagination) {
        return getPostByStatus(EPostStatus.PUBLISHED, pagination);
    }

    @Override
    public Page<PostDto> getPostsArchived(PostPagination pagination) {
        return getPostByStatus(EPostStatus.ARCHIVED, pagination);
    }

    @Override
    public Page<PostDto> getPostsDrafted(PostPagination pagination) {
        return getPostByStatus(EPostStatus.DRAFT, pagination);
    }

    @Override
    public Page<PostDto> getPostsByTag(String tagName, PostPagination pagination) {

        Optional<Tag> tagFound = tagRepository.findByNameIgnoreCase(tagName);
        Page<Post> posts = new PageImpl<>(new ArrayList<>());

        if (tagFound.isPresent()) {
            Pageable pageable = UtilityFunctions.getPageable(pagination);
            posts = postRepository.findAllByTags(tagFound.get(), pageable);
        }

        return posts.map(PostDto::new);
    }

    @Override
    public Page<CommentDto> getCommentsByPostId(Long id, CommentPagination pagination) throws ResourceNotFoundException {
        Post post = getPostOrThrowException(id);
        Pageable pageable = UtilityFunctions.getPageable(pagination);

        Page<Comment> comments = commentRepository.findAllByPost(post, pageable);

        return comments.map(CommentDto::new);
    }

    @Override
    public void deletePostById(Long id) throws ResourceNotFoundException {
        Post postFound = getPostOrThrowException(id);
        UtilityFunctions.denyAccessUnlessGranted(
                postFound.getUser(),
                "Access denied! You don't have access to update this Post"
        );
        postRepository.delete(postFound);
    }

    private PostDetailsDto getPostDtoWithComment(Post post) {

        List<Comment> comments = commentRepository.findAllByPost(post);
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

    private Page<PostDto> getPostByStatus(EPostStatus status, PostPagination pagination) {
        Pageable pageable = UtilityFunctions.getPageable(pagination);
        Page<Post> posts = postRepository.findAllByStatus(status, pageable);

        return posts.map(PostDto::new);
    }
}