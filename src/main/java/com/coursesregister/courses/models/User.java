
package com.coursesregister.courses.models;

import com.coursesregister.courses.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

    
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roles;
    
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    private List<Course> coursesCreated;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Enrollment> usersEnrollments;

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

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
      
}


