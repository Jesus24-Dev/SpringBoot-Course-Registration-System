
package com.coursesregister.courses.services;

import com.coursesregister.courses.models.Course;
import com.coursesregister.courses.repository.CourseRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    
    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
    
    public Course getCourseById(UUID id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found. Id: " + id));
        return course;
    }
}
