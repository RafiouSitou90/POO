package com.rafdev.prova.blog.api.util;

import com.rafdev.prova.blog.api.entity.Role;
import com.rafdev.prova.blog.api.entity.Tag;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.enums.ERole;
import com.rafdev.prova.blog.api.pagination.AbstractBasePagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

    public static Pageable getPageable(AbstractBasePagination pagination) {
        Sort sort = Sort.by(pagination.getDirection(), pagination.getSortBy());

        return PageRequest.of(pagination.getPage(), pagination.getSize(), sort);
    }

    public static User getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    public static void denyAccessUnlessGranted(User user, String message) {
        if (!canAccess(user, UtilityFunctions.getUserAuthenticated())) {
            throw new AccessDeniedException(message);
        }
    }

    private static Boolean canAccess(User user, User userAuthenticated) {
        if (user.equals(userAuthenticated)) {
            return true;
        } else {
            for (Role role: userAuthenticated.getRoles()) {
                if (role.getName() == ERole.ROLE_ADMIN || role.getName() == ERole.ROLE_SUPER_ADMIN) {
                    return true;
                }
            }
        }

        return false;
    }
}
