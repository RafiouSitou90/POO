package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.enums.ERole;
import com.rafdev.prova.blog.api.entity.Role;

public interface RoleService {
    Role getOrCreateByName(ERole name);
}
