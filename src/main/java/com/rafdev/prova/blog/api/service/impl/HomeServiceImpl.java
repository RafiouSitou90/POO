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
        apiEntryPointList.add(getEntryPoint("Users", "User"));
        apiEntryPointList.add(getEntryPoint("Categories", "Category"));
        apiEntryPointList.add(getEntryPoint("Posts", "Post"));
        apiEntryPointList.add(getEntryPoint("Comments", "Comment"));

        return apiEntryPointList;
    }

    private Entrypoint getEntryPoint(String resourceNamePlural, String resourceNameSingle) {
        Entrypoint entrypoint = new Entrypoint();
        entrypoint.setName(resourceNamePlural);

        List<Endpoint> endpoints = new ArrayList<>();
        endpoints.add(new Endpoint(
                String.format("%s list", resourceNamePlural),
                String.format("Get the full list of %s provided by the API.", resourceNamePlural.toLowerCase()),
                HttpMethod.GET, resourceNamePlural.toLowerCase()));

        endpoints.add(new Endpoint(
                String.format("Single %s resource", resourceNameSingle.toLowerCase()),
                String.format("Get single %s resource provided by the API.", resourceNameSingle.toLowerCase()),
                HttpMethod.GET, String.format("%s/{id}", resourceNamePlural.toLowerCase())));

        endpoints.add(new Endpoint(
                String.format("Add %s resource", resourceNameSingle.toLowerCase()),
                String.format("Add a single %s resource in the API.", resourceNameSingle.toLowerCase()),
                HttpMethod.POST, resourceNamePlural.toLowerCase()));

        endpoints.add(new Endpoint(
                String.format("Update full %s resource", resourceNameSingle.toLowerCase()),
                String.format("Update a single %s resource in the API.", resourceNameSingle.toLowerCase()),
                HttpMethod.PUT, String.format("%s/{id}", resourceNamePlural.toLowerCase())));

        endpoints.add(new Endpoint(
                String.format("Delete %s resource", resourceNameSingle.toLowerCase()),
                String.format("Delete a single %s resource in the API.", resourceNameSingle.toLowerCase()),
                HttpMethod.DELETE, String.format("%s/{id}", resourceNamePlural.toLowerCase())));

        entrypoint.setEndpoints(endpoints);

        return entrypoint;
    }
}
