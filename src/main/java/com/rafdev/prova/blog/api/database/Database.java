package com.rafdev.prova.blog.api.database;

import com.github.javafaker.Faker;
import com.rafdev.prova.blog.api.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Database {

    private final List<Category> categories = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private final List<Post> posts = new ArrayList<>();
    private final List<Role> roles = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final Faker faker = new Faker(new Locale("pt-BR"));

    public Database() {
        initializeRoles();
        initializeUsers();
        initializeCategories();
        initializePosts();
        initializeComments();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<User> getUsers() {
        return users;
    }

    private void initializeRoles() {
        this.roles.add(new Role(1L, ERole.ROLE_USER));
        this.roles.add(new Role(2L, ERole.ROLE_ADMIN));
        this.roles.add(new Role(3L, ERole.ROLE_SUPER_ADMIN));
    }

    private void initializeUsers() {
        for (int i = 1; i <= 10; i++) {
            String username = faker.name().username();
            String email = faker.internet().emailAddress(username);
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String password = faker.internet().password();
            List<Role> userRoles = new ArrayList<>();

            if (i <= 3) {
                userRoles.add(getRoles().get(0));
            } else if (i <= 7) {
                userRoles.add(getRoles().get(0));
                userRoles.add(getRoles().get(1));
            } else {
                userRoles.add(getRoles().get(0));
                userRoles.add(getRoles().get(1));
                userRoles.add(getRoles().get(2));
            }

            User user = new User((long) i, username, email, password, firstName, lastName, userRoles);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(null);

            this.users.add(user);
        }
    }

    private void initializeCategories() {
        for (int i = 1; i <= 10; i++) {
            Category category = new Category((long) i, faker.hobbit().character());
            category.setCreatedAt(LocalDateTime.now());
            category.setUpdatedAt(null);

            this.categories.add(category);
        }
    }

    private void initializePosts() {
        int l = 1;
        for (int i = 1; i <= 10; i++) {
            User user = users.get(i - 1);
            Category category = categories.get(i - 1);
            for (int j = 1; j <= 10; j++) {
                String title = faker.hobbit().quote();
                String content = faker.matz().quote();
                String imageUrl = faker.internet().image();
                Post post = new Post((long) l, title, content, imageUrl, user, category, new Date());
                post.setCreatedAt(LocalDateTime.now());
                post.setUpdatedAt(null);

                this.posts.add(post);

                l++;
            }
        }
    }

    private void initializeComments() {
        int l = 1;
        for (int i = 1; i <= 10; i++) {
            User user = users.get(i - 1);
            Post post = posts.get(i - 1);
            for (int j = 1; j <= 10; j++) {
                String content = faker.dune().saying();
                Comment comment = new Comment((long) l, content, user, post, new Date());
                comment.setCreatedAt(LocalDateTime.now());
                comment.setUpdatedAt(null);

                this.comments.add(comment);
                l++;
            }
        }
    }
}
