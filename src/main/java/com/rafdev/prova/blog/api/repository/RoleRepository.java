package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
