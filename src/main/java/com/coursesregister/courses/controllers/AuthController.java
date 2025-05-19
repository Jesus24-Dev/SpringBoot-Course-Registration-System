package com.coursesregister.courses.controllers;

import com.coursesregister.courses.dtos.UserRequest;
import com.coursesregister.courses.models.User;
import com.coursesregister.courses.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    
    private final UserService userService;
    
    @Autowired
    public AuthController(UserService userService){
       this.userService = userService;
    }
    
    @GetMapping({"/", "/login"})
    public String loginPage(){
        return "login";
    } 
    
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("user") UserRequest user, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return "register";
        }
        userService.registerUser(user.getUsername(), user.getPassword(), user.getRoles());
        return "login";
    }
}
