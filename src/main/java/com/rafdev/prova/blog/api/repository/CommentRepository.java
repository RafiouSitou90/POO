package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.database.Database;
import com.rafdev.prova.blog.api.entity.Comment;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CommentRepository {

    private final List<Comment> comments;

    public CommentRepository(Database database) {
        this.comments = database.getComments();
    }

    public List<Comment> findAll() {
        return comments;
    }

    public List<Comment> findAllByPostId(Long postId) {
        List<Comment> postComments = new ArrayList<>();

        for (Comment comment: comments) {
            if (comment.getPost().getId().equals(postId)) {
                postComments.add(comment);
            }
        }

        return postComments;
    }

    public Comment save(Comment comment) {
        comments.add(comment);

        return comment;
    }

    public Comment update(Comment comment) {
        Comment commentBD = findById(comment.getId());

        if (commentBD != null) {
            comments.set(comments.indexOf(commentBD), comment);
        }

        return comment;
    }

    public void delete(Long id) {
        comments.removeIf(comment -> Objects.equals(comment.getId(), id));
    }

    public Comment findById(Long id) {
        for (Comment comment: comments) {
            if (comment.getId().equals(id)) {
                return comment;
            }
        }

        return null;
    }

    public boolean existsById(Long id) {
        for (Comment comment: comments) {
            if (comment.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
