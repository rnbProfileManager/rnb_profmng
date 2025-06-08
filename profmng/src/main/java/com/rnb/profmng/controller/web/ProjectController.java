package com.rnb.profmng.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController{
	
	@GetMapping("/project")
	public String showProjectPage() {
		return "web/project";
	}
}