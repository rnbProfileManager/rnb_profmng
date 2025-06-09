package com.rnb.profmng.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.service.ProjectService;

@Controller
public class ProjectController{
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/project")
	public String showProjectPage() {
		return "web/project";
	}
	
	@GetMapping("/insertProject")
	public String showInsertProjectPage() {
		return "project/insertProject";
	}
	
	@GetMapping("/selectProject")
	public String showSelectProjectPage() {
		return "project/selectProject";
	}
	
	@GetMapping("/updateProject")
	public String showUpdateProjectPage() {
		return "project/updateProject";
	}
	
	@PostMapping("/insertData")
	public String insertProject(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {
		try {
			int result = projectService.insertProject(projectDto);

		    if (result > 0) {
		        redirectAttributes.addFlashAttribute("insertResult", "success");
		    } else {
		        redirectAttributes.addFlashAttribute("insertResult", "fail");
		    }
		}catch (DuplicateKeyException e) {
	        // 중복 키 오류 발생 시
	        redirectAttributes.addFlashAttribute("insertResult", "duplicate");
	    } catch (Exception e) {
	        // 기타 예외
	        redirectAttributes.addFlashAttribute("insertResult", "fail");
	    }
		return "redirect:/project";
	}
}