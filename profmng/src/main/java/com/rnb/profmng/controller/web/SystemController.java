package com.rnb.profmng.controller.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemController{
	
	@GetMapping("/system")
	public String showProjectPage() {
		return "web/system";
	}
}