package com.rafdev.prova.blog.api.request;

import java.util.List;

public class UserRequest {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getRoles() {
        return roles;
    }
}
