package com.rafdev.prova.blog.api.pagination;

public class CategoryPagination extends AbstractBasePagination {

    private String sortBy = "name";

    @Override
    public String getSortBy() {
        return sortBy;
    }

    @Override
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
