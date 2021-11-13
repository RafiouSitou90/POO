package com.rafdev.prova.blog.api.controller.admin;

import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.enums.ERole;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/admin/users")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
@Api(tags = "Administration")
@Tags(value = @Tag(name = "Administration", description = "Administration Resources"))
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/change-roles/{id}")
    @Operation(summary = "Update User roles", description = "Change or Update User Roles by User id")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<UserDto> changeUserRoles(@PathVariable("id") Long id, @RequestBody ERole[] roles)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.changeUserRoles(id, roles), HttpStatus.OK);
    }

    @PatchMapping("/activate/{id}")
    @Operation(summary = "Activate User", description = "Activate User by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> activateUser(@PathVariable("id") Long id) throws ResourceNotFoundException {
        userService.activateUser(id);

        return new ResponseEntity<>("User successfully activated", HttpStatus.OK);
    }

    @PatchMapping("/deactivate/{id}")
    @Operation(summary = "Deactivate User", description = "Deactivate User by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deactivateUser(@PathVariable("id") Long id) throws ResourceNotFoundException {
        userService.deactivateUser(id);

        return new ResponseEntity<>("User successfully deactivated", HttpStatus.OK);
    }
}
