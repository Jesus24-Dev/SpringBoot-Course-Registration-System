
package com.coursesregister.courses.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseRequest {
    
    @NotBlank(message = "Title can't be empty")
    @Size(min = 3, message = "Insert a valid title")
    private String title;
    
    @NotBlank(message = "Description can't be empty")
    @Size(min = 10, message = "The description requires a minimum of 6 characters")
    private String description;
    
    @NotBlank(message = "Username can't be empty.")
    private String username;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
