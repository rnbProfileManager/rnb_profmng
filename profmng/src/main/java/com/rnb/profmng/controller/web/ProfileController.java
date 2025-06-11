package com.rnb.profmng.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController{
	@GetMapping("/profile")
	public String showProjectPage() {
		return "web/profile";
	}
	
	@GetMapping("/selectProfile")
	public String showSelectProfilePage() {
		return "web/selectProfile";
	}
	
	@GetMapping("/empProfileInsert")
	public String showEmpProfileInsertPage() {
		return "web/empProfileInsert";
	}
	
	@GetMapping("/empProfileManage")
	public String showEmpProfileManagePage() {
		return "web/empProfileManage";
	}
	
	@GetMapping("/empProjectInsert")
	public String showEmpProjectInsertPage() {
		return "web/empProjectInsert";
	}
	
	@GetMapping("/empProjectManage")
	public String showEmpProjectManagePage() {
		return "web/empProjectManage";
	}
	
	@GetMapping("/empAbilityInsert")
	public String showEmpAbilityInserttPage() {
		return "web/empAbilityInsert";
	}
	
	@GetMapping("/empAbilityManage")
	public String showEmpAbilityManagePage() {
		return "web/empAbilityManage";
	}
}