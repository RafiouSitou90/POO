package com.rafdev.prova.blog.api.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "tab_roles")
public class Role extends AbstractBaseEntity{

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(Long id, ERole name) {
        this.name = name;
    }

    public Role() {
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
