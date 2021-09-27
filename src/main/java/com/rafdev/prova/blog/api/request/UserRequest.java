package com.rafdev.prova.blog.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UserRequest {

    @NotBlank(message = "The username cannot be blank")
    @Size.List ({
            @Size(min = 6, message = "The username must be at least {min} characters"),
            @Size(max = 100, message = "The username must be less than {max} characters")
    })
    private String username;

    @NotBlank(message = "The email cannot be blank")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "The password cannot be blank")
    @Size.List ({
            @Size(min = 8, message = "The password must be at least {min} characters"),
            @Size(max = 100, message = "The password must be less than {max} characters")
    })
    private String password;

    @NotBlank(message = "The firstName cannot be blank")
    @Size.List ({
            @Size(min = 3, message = "The firstName must be at least {min} characters"),
            @Size(max = 100, message = "The firstName must be less than {max} characters")
    })
    private String firstName;

    @NotBlank(message = "The lastName cannot be blank")
    @Size.List ({
            @Size(min = 3, message = "The lastName must be at least {min} characters"),
            @Size(max = 100, message = "The lastName must be less than {max} characters")
    })
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

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
