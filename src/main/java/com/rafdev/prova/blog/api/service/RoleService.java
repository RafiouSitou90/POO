package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.entity.ERole;
import com.rafdev.prova.blog.api.entity.Role;

public interface RoleService {
    Role getOrCreateByName(ERole name);
}
