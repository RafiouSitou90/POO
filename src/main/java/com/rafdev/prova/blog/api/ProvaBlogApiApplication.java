package com.rafdev.prova.blog.api;

import com.rafdev.prova.blog.api.database.Database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProvaBlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvaBlogApiApplication.class, args);
    }

    @Bean
    public Database database() {
        return new Database();
    }

}
