package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.Tag;
import com.rafdev.prova.blog.api.enums.EPostStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Boolean existsByTitleIgnoreCase(String title);

    Page<Post> findAllByStatus(EPostStatus status, Pageable pageable);

    Page<Post> findAllByTags(Tag tag, Pageable pageable);
}
