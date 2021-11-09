package com.rafdev.prova.blog.api.dto.user;

import com.rafdev.prova.blog.api.dto.AbstractBaseDto;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.util.UtilityFunctions;

import java.util.Set;

public class UserDto extends AbstractBaseDto {

    private String username;
    private String email;
    private String fullName;
    private Set<String> roles;

    public UserDto(User user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.roles = UtilityFunctions.rolesToListOfString(user.getRoles());
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
