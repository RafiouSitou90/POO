package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.response.Entrypoint;

import java.util.List;

public interface HomeService {

    List<Entrypoint> getEntryPoints();
}
