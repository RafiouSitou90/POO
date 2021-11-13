package com.rafdev.prova.blog.api.builder;

import com.rafdev.prova.blog.api.entity.Role;
import com.rafdev.prova.blog.api.entity.User;

import java.util.Set;

public class UserBuilder {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Role> roles;

    public UserBuilder withUsername(final String username) {
        this.username = username;

        return this;
    }

    public UserBuilder withEmail(final String email) {
        this.email = email;

        return this;
    }

    public UserBuilder withPassword(final String password) {
        this.password = password;

        return this;
    }

    public UserBuilder withFirstName(final String firstName) {
        this.firstName = firstName;

        return this;
    }

    public UserBuilder withLastName(final String lastName) {
        this.lastName = lastName;

        return this;
    }

    public UserBuilder withRoles(final Set<Role> roles) {
        this.roles = roles;

        return this;
    }

    public UserBuilder withRoles(final Role role) {
        if (roles.contains(role)) {
            return this;
        }

        roles.add(role);

        return this;
    }

    public User build() {
        return new User(username, email, password, firstName, lastName, roles);
    }
}
