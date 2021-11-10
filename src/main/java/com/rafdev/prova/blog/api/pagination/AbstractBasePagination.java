package com.rafdev.prova.blog.api.pagination;

import org.springframework.data.domain.Sort;

public abstract class AbstractBasePagination {

    protected int page = 0;
    protected int size = 10;
    protected Sort.Direction direction = Sort.Direction.ASC;
    protected String sortBy;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
