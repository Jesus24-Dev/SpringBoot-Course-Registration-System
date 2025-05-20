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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminCourseController {
    
    private final CourseService courseService;
    
    @Autowired
    public AdminCourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @GetMapping("/courses")
    public String getAdminCourses(Model model, Principal principal) {
        List<Course> coursesList = courseService.getCoursesByAdmin(principal.getName());
        model.addAttribute("courses", coursesList);
        model.addAttribute("course", new CourseRequest()); 
        return "courses"; 
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "edit-course";
    }
    
    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute("course") CourseRequest course, 
                               BindingResult result, 
                               Model model, 
                               Principal principal,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<Course> coursesList = courseService.getCoursesByAdmin(principal.getName());
            model.addAttribute("courses", coursesList);
            return "courses";
        }

        courseService.createCourse(course.getTitle(), course.getDescription(), principal.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Course created succesfully");
        return "redirect:/admin/courses";
    }
    
    @PostMapping("/update/{id}")
    public String updateCourseDescription(@PathVariable UUID id, 
                                        @RequestParam String description,
                                        RedirectAttributes redirectAttributes) {
        courseService.updateCourseDescription(description, id);
        redirectAttributes.addFlashAttribute("successMessage", "Course updated succesfully");
        return "redirect:/admin/courses";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        courseService.deleteCourse(id);
        redirectAttributes.addFlashAttribute("successMessage", "Course deleted succesfully");
        return "redirect:/admin/courses";
    }
}