package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.entity.ERole;
import com.rafdev.prova.blog.api.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole roleUser);
}
