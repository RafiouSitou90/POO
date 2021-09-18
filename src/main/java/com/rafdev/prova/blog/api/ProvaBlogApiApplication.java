package com.rafdev.prova.blog.api;

import com.rafdev.prova.blog.api.database.Database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProvaBlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvaBlogApiApplication.class, args);
    }

    @Bean
    public Database database() {
        return new Database();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
