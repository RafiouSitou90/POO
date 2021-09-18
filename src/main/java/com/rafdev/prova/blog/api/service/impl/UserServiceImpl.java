package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.UserDto;
import com.rafdev.prova.blog.api.entity.ERole;
import com.rafdev.prova.blog.api.entity.Role;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.repository.RoleRepository;
import com.rafdev.prova.blog.api.repository.UserRepository;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    private final String resourceName = "User";
    private final AtomicLong idCounter = new AtomicLong(10);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user: users) {
            UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getFirstName(),
                    user.getLastName(),
                    user.getRoles());

            usersDto.add(userDto);
        }

        return usersDto;
    }

    @Override
    public UserDto saveUser(UserRequest userRequest) throws ResourceAlreadyExistsException {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResourceAlreadyExistsException(resourceName, "Email", userRequest.getEmail());
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResourceAlreadyExistsException(resourceName, "Username", userRequest.getUsername());
        }

        List<Role> roles = setUserRoles(userRequest.getRoles());

        String passwordHashed = passwordEncoder.encode(userRequest.getPassword());

        User user = new User(idCounter.incrementAndGet(), userRequest.getUsername(), userRequest.getEmail(),
                passwordHashed, userRequest.getFirstName(), userRequest.getLastName(), roles);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(null);

        User userCreated = userRepository.save(user);

        return setUserDTO(userCreated);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        return setUserDTO(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        userRepository.delete(id);
    }

    @Override
    public UserDto updateUserById(Long id, UserRequest userRequest) {
        User userFound = userRepository.findById(id);

        if (userFound == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        List<Role> roles = setUserRoles(userRequest.getRoles());

        User userFoundWithUsername = userRepository.findByUsername(userRequest.getUsername());
        if (userFoundWithUsername != null && !Objects.equals(userFound.getUsername(), userFoundWithUsername.getUsername())) {
            throw new ResourceNotFoundException(resourceName, "Username", userRequest.getUsername());
        }

        User userFoundWithEmail = userRepository.findByEmail(userRequest.getEmail());
        if (userFoundWithEmail != null && !Objects.equals(userFound.getEmail(), userFoundWithEmail.getEmail())) {
            throw new ResourceNotFoundException(resourceName, "Email", userRequest.getEmail());
        }

        String passwordHashed = passwordEncoder.encode(userRequest.getPassword());

        userFound.setUsername(userRequest.getUsername());
        userFound.setEmail(userRequest.getEmail());
        userFound.setPassword(passwordHashed);
        userFound.setFirstName(userRequest.getFirstName());
        userFound.setLastName(userRequest.getLastName());
        userFound.setRoles(roles);
        userFound.setUpdatedAt(LocalDateTime.now());

        User userUpdated = userRepository.update(userFound);

        return setUserDTO(userUpdated);
    }

    private UserDto setUserDTO(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getRoles());
    }

    private List<Role> setUserRoles(List<String> strRoles) {
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "super_admin" -> {
                        Role superAdminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
                        roles.add(superAdminRole);
                    }
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                    }
                }
            });
        }

        return roles;
    }
}
