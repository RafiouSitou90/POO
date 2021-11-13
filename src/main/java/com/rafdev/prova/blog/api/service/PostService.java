package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.dto.post.PostDetailsDto;
import com.rafdev.prova.blog.api.dto.post.PostDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CommentPagination;
import com.rafdev.prova.blog.api.pagination.PostPagination;
import com.rafdev.prova.blog.api.request.PostRequest;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface PostService {

    PostDto savePost(PostRequest postRequest) throws ResourceNotFoundException, ResourceAlreadyExistsException;

    PostDto updatePostById(Long id, PostRequest postRequest) throws ResourceNotFoundException, ResourceAlreadyExistsException;

    PostDto updatePostById(Long id, Map<String, Object> postRequest) throws ResourceNotFoundException, ResourceAlreadyExistsException;

    Page<PostDto> getPosts(PostPagination pagination);

    PostDetailsDto getPostById(Long id) throws ResourceNotFoundException;

    PostDto publishPostById(Long id, Map<String, Object> postRequest) throws ResourceNotFoundException;

    PostDto archivePostById(Long id) throws ResourceNotFoundException;

    PostDto draftPostById(Long id) throws ResourceNotFoundException;

    Page<PostDto> getPostsPublished(PostPagination pagination);

    Page<PostDto> getPostsArchived(PostPagination pagination);

    Page<PostDto> getPostsDrafted(PostPagination pagination);

    Page<PostDto> getPostsByTag(String tagName, PostPagination pagination);

    Page<CommentDto> getCommentsByPostId(Long id, CommentPagination pagination) throws ResourceNotFoundException;

    void deletePostById(Long id) throws ResourceNotFoundException;
}
