package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.response.Endpoint;
import com.rafdev.prova.blog.api.response.Entrypoint;
import com.rafdev.prova.blog.api.service.HomeService;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Override
    public List<Entrypoint> getEntryPoints() {
        final List<Entrypoint> apiEntryPointList = new ArrayList<>();
        apiEntryPointList.add(getCategoryEntryPoint());

        return apiEntryPointList;
    }

    private Entrypoint getCategoryEntryPoint() {
        Entrypoint categoryEntrypoint = new Entrypoint();
        categoryEntrypoint.setName("Categories");

        List<Endpoint> categoryEndpoints = new ArrayList<>();
        categoryEndpoints.add(new Endpoint(
                "Categories list", "Get the full list of categories provided by the API.",
                HttpMethod.GET, "categories"));

        categoryEndpoints.add(new Endpoint(
                "Single category resource", "Get single category resource provided by the API.",
                HttpMethod.GET, "categories/{id}"));

        categoryEndpoints.add(new Endpoint(
                "Add category resource", "Add a single category resource in the API.",
                HttpMethod.POST, "categories"));

        categoryEndpoints.add(new Endpoint(
                "Update full category resource", "Update a single category resource in the API.",
                HttpMethod.PUT, "categories/{id}"));

        categoryEndpoints.add(new Endpoint(
                "Delete category resource", "Delete a single category resource in the API.",
                HttpMethod.DELETE, "categories/{id}"));

        categoryEntrypoint.setEndpoints(categoryEndpoints);

        return categoryEntrypoint;
    }
}
