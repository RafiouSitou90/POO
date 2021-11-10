package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmailIgnoreCase(String email);

    Boolean existsByUsernameIgnoreCase(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.username = ?1")
    Optional<User> findByUsernameOrEmailIgnoreCase(String username);
}
