package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.entity.ERole;
import com.rafdev.prova.blog.api.entity.Role;
import com.rafdev.prova.blog.api.repository.RoleRepository;
import com.rafdev.prova.blog.api.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getOrCreateByName(ERole name) {
        Optional<Role> role = roleRepository.findByName(name);

        if (role.isEmpty()) {
            return roleRepository.save(new Role(name));
        } else {
            return role.get();
        }
    }
}
