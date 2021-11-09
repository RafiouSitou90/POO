package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.entity.Tag;
import com.rafdev.prova.blog.api.repository.TagRepository;
import com.rafdev.prova.blog.api.service.TagService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getOrCreateByName(String name) {

        Optional<Tag> tag = tagRepository.findByNameIgnoreCase(name);

        if (tag.isEmpty()) {
            return tagRepository.save(new Tag(name));
        } else {
            return tag.get();
        }
    }
}
