package com.coursesregister.courses.controllers;

import com.coursesregister.courses.dtos.CourseRequest;
import com.coursesregister.courses.models.Course;
import com.coursesregister.courses.services.CourseService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminCourseController {
    
    private final CourseService courseService;
    
    @Autowired
    public AdminCourseController(CourseService courseService){
        this.courseService = courseService;
    }
    
    @GetMapping("/courses")
    public String getAdminCourses(Model model, Principal principal){
        List<Course> coursesList = courseService.getCoursesByAdmin(principal.getName());
        model.addAttribute("courses", coursesList);
        
        return "courses";  
    }
    
    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute("course") CourseRequest course, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        
        courseService.createCourse(course.getTitle(), course.getDescription(), course.getUsername());     
        return "redirect:courses?created=true";
    }
    
    @PostMapping("/create/{id}")
    public String updateCourseDescription(@PathVariable UUID id, String description){
        courseService.updateCourseDescription(description, id);
        
        return "redirect:courses?updated=true";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable UUID id){
        courseService.deleteCourse(id);
        return "redirect:courses?deleted=true";
    }
}
