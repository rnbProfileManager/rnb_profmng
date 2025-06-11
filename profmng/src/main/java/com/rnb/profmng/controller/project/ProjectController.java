package com.rnb.profmng.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController{

    // Web 페이지 변경 매핑
	@GetMapping("/project")
	public String showProjectPage() {
		return "project/project";
	}
}