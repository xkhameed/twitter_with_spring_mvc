package org.example.controller;


import org.example.dto.RegisterDTO;
import org.example.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String registerPage(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String register( @ModelAttribute RegisterDTO dto ) {
        authService.register(dto);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutPage(){
        return "auth/logout";
    }

}
