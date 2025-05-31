package com.rnb.profmng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHome() {
		System.out.println("Home Controller 호출");
		return "index";
	}
}
