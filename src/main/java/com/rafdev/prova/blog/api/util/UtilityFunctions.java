package com.rafdev.prova.blog.api.util;

import com.rafdev.prova.blog.api.entity.Role;
import com.rafdev.prova.blog.api.entity.Tag;

import java.util.HashSet;
import java.util.Set;

public class UtilityFunctions {

    public static Set<String> tagsToListOfString(Set<Tag> tags) {
        Set<String> strTags = new HashSet<>();
        tags.forEach(tag -> strTags.add(tag.getName()));

        return strTags;
    }

    public static Set<String> rolesToListOfString(Set<Role> roles) {
        Set<String> strRoles = new HashSet<>();
        roles.forEach(role -> strRoles.add(role.getName().toString()));

        return strRoles;
    }
}
