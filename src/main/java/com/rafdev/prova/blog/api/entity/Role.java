package com.rafdev.prova.blog.api.entity;

import com.rafdev.prova.blog.api.enums.ERole;

import javax.persistence.*;

@Entity
@Table(name = "tab_roles")
public class Role extends AbstractBaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 30)
    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
