package com.rafdev.prova.blog.api.response;

import java.util.List;

public class Entrypoint {

    private String name;
    private List<Endpoint> endpoints;

    public Entrypoint() {
    }

    public Entrypoint(String name, List<Endpoint> endpoints) {
        this.name = name;
        this.endpoints = endpoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }
}
