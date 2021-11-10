package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.post.PostDetailsDto;
import com.rafdev.prova.blog.api.dto.post.PostDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.request.PostRequest;

import java.util.List;

public interface PostService {

    PostDto savePost(PostRequest postRequest) throws ResourceNotFoundException, ResourceAlreadyExistsException;

    PostDto updatePostById(Long id, PostRequest postRequest) throws ResourceNotFoundException, ResourceAlreadyExistsException;

    List<PostDto> getPosts();

    PostDetailsDto getPostById(Long id) throws ResourceNotFoundException;

    void deletePostById(Long id) throws ResourceNotFoundException;
}
