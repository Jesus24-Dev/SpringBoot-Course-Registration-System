
package com.coursesregister.courses.services;

import com.coursesregister.courses.models.Course;
import com.coursesregister.courses.models.User;
import com.coursesregister.courses.repository.CourseRepository;
import com.coursesregister.courses.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public CourseService(CourseRepository courseRepository, UserRepository userRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }
    
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
    
    public Course getCourseById(UUID id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found. Id: " + id));
        return course;
    }
    
    public List<Course> getCoursesByAdmin(String username){
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found. Username: " + username));
        
        return user.getCoursesCreated();
    }
    
    public void createCourse(String title, String description, String username){
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found. Username: " + username));
        
        Course courseCreated = new Course();
        courseCreated.setTitle(title);
        courseCreated.setDescription(description);
        courseCreated.setCreatedBy(user);
        
        courseRepository.save(courseCreated);
    }
    
    public void updateCourseDescription(String description, UUID courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found. Id: " + courseId));
        
        course.setDescription(description);
        courseRepository.save(course);
    }
    
    public void deleteCourse(UUID courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found. Id: " + courseId));
        
        courseRepository.delete(course);
    }
}
