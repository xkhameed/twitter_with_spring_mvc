package org.example.controller;

import org.example.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post-add")
    public String postAddPage(){
        return "post_add";
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/post-add")
    public String postAdd(@RequestParam("title") String title, @RequestParam("content") String content) {
        postService.createPost(title, content);
        return "redirect:/";
    }





    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/global-posts")
    public ModelAndView globalPosts(){
        ModelAndView modelAndView = new ModelAndView("posts");
        modelAndView.addObject("posts", postService.getAll());
        return modelAndView;
    }


}
