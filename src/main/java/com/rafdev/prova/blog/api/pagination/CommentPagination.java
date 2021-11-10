package com.rafdev.prova.blog.api.pagination;

import org.springframework.data.domain.Sort;

public class CommentPagination extends AbstractBasePagination {

    private String sortBy = "publishedAt";
    private Sort.Direction direction = Sort.Direction.DESC;

    @Override
    public String getSortBy() {
        return sortBy;
    }

    @Override
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public Sort.Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }
}
