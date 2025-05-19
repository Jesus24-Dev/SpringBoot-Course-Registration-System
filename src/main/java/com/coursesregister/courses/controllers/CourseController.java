
package com.coursesregister.courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
    
    @GetMapping("/home")
    public String homePage(){
        return "/home";
    }
}
