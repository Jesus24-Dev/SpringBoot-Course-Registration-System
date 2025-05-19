
package com.coursesregister.courses.dtos;

import com.coursesregister.courses.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {
    
    @NotBlank(message = "Username can't be empty")
    @Size(min = 3, message = "Insert a valid username")
    private String username;
    
    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, message = "The password requires a minimum of 6 characters")
    private String password;
    
    @NotNull(message = "Please choose a rol.")
    private Roles roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
    
    
}
