package com.rafdev.prova.blog.api.pagination;

public class UserPagination extends AbstractBasePagination {

    private String sortBy = "username";

    @Override
    public String getSortBy() {
        return sortBy;
    }

    @Override
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
