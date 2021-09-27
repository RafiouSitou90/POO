package com.rafdev.prova.blog.api.response;

import org.springframework.http.HttpMethod;

public class Endpoint {

    private String name;
    private String description;
    private HttpMethod method;
    private String url;

    public Endpoint(String name, String description, HttpMethod method, String url) {
        this.name = name;
        this.description = description;
        this.method = method;
        this.url = String.format("/api/v1/%s", url);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
