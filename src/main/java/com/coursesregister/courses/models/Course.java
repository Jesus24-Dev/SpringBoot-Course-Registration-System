
package com.coursesregister.courses.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="courses")
public class Course {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String title;
    private String description;

    @ManyToOne
    private User createdBy;
    
    @OneToMany
    private List<Enrollment> coursesEnrollments = new ArrayList<>();

    public Course() {
    }

    public Course(UUID id, String title, String description, User createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Enrollment> getCoursesEnrollments() {
        return coursesEnrollments;
    }

    public void setCoursesEnrollments(List<Enrollment> coursesEnrollments) {
        this.coursesEnrollments = coursesEnrollments;
    }
       
}
