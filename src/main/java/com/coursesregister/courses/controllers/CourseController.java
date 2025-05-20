
package com.coursesregister.courses.controllers;

import com.coursesregister.courses.models.Course;
import com.coursesregister.courses.services.CourseService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourseController {
          
    
    private final CourseService courseService;
    
    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }
    
    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
    
    @GetMapping("/courses")
    public String coursesPage(Model model){
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }
    
    @GetMapping("/courses/{id}")
    public String courseDetail(Model model, @PathVariable UUID id){
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "course-details";
    }
}
