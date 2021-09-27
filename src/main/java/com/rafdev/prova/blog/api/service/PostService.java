package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.PostDto;
import com.rafdev.prova.blog.api.request.PostRequest;

import java.util.List;

public interface PostService {

    PostDto savePost(PostRequest postRequest);

    PostDto updatePostById(Long id, PostRequest postRequest);

    List<PostDto> getPosts();

    PostDto getPostById(Long id);

    void deletePostById(Long id);
}
