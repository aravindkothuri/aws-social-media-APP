package com.social.controller;

import com.social.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SocialController {

    private final PostService postService;

    public SocialController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String feed(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "index";
    }

    @PostMapping("/posts")
    public String createPost(@RequestParam String authorName,
                             @RequestParam String content,
                             @RequestParam(required = false) String imageUrl) {
        String defaultAvatar = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=200&q=80";
        postService.createPost(authorName, defaultAvatar, content, imageUrl);
        return "redirect:/";
    }

    @PostMapping("/posts/{id}/like")
    public String likePost(@PathVariable Long id) {
        postService.likePost(id);
        return "redirect:/";
    }
}
