package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.entity.Tag;

public interface TagService {

    Tag getOrCreateByName(String name);
}
