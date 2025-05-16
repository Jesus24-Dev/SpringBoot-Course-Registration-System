
package com.coursesregister.courses.models;

import com.coursesregister.courses.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

    
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(unique=true)
    private String username;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Roles roles;
    
    @OneToMany(mappedBy = "createdBy")
    private List<Course> coursesCreated = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<Enrollment> usersEnrollments = new ArrayList<>();

    public User() {
    }

    public User(UUID id, String username, String password, Roles roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }  

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public List<Course> getCoursesCreated() {
        return coursesCreated;
    }

    public void setCoursesCreated(List<Course> coursesCreated) {
        this.coursesCreated = coursesCreated;
    }

    public List<Enrollment> getUsersEnrollments() {
        return usersEnrollments;
    }

    public void setUsersEnrollments(List<Enrollment> usersEnrollments) {
        this.usersEnrollments = usersEnrollments;
    }
      
}


