package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Boolean existsByTitleIgnoreCase(String title);

    Post findByTitleIgnoreCase(String title);
}
