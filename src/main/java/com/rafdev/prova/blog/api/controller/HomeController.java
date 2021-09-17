package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.response.Entrypoint;

import com.rafdev.prova.blog.api.service.HomeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Entrypoint>> getEntryPoints() {
        return new ResponseEntity<>(homeService.getEntryPoints(), HttpStatus.OK);
    }
}
