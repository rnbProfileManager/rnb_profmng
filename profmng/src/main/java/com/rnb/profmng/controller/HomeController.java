package com.rnb.profmng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
    public String redirectToStatic() {
        return "redirect:/index.html";
    }
	
	@GetMapping("/login")
    public String showLoginPage() {
        return "user/login";
    }
}
