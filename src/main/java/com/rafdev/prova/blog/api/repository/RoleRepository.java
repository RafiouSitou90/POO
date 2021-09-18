package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.database.Database;
import com.rafdev.prova.blog.api.entity.ERole;
import com.rafdev.prova.blog.api.entity.Role;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {

    private final List<Role> roles;

    public RoleRepository(Database database) {
        this.roles = database.getRoles();
    }

    public List<Role> findAll() {
        return roles;
    }

    public Role findByName(ERole name) {
        for (Role role: roles) {
            if (role.getName().equals(name)) {
                return role;
            }
        }

        return null;
    }
}
