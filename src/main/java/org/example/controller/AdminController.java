package org.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView("users");
        return modelAndView;
    }


}
