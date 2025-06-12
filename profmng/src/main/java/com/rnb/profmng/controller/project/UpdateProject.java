package com.rnb.profmng.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.service.ProjectService;

@Controller
public class UpdateProject{

	@Autowired
	private ProjectService projectService;

	@GetMapping("/updateProject")
	public String showUpdateProjectPage() {
		return "project/updateProject";
	}
	
	@GetMapping("/updateProjectWithCd")
	public String showUpdateProjectPage(@RequestParam String projectCd, Model model) {
		model.addAttribute("projectCd", projectCd);
		return "project/project";
	}
	
	@PostMapping("/updateProjectButton")
	public String updateButton(@RequestParam String projectCd) {
		return "redirect:/updateProjectWithCd?projectCd=" + projectCd;
	}
	
//	@PostMapping("/updateProjectData")
//	public String updateProject(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {
//		try {
//			Boolean result = projectService.updateProject(projectDto);
//		    if (result != null) {
//		        redirectAttributes.addFlashAttribute("insertResult", "success");
//		    } else {
//		        redirectAttributes.addFlashAttribute("insertResult", "fail");
//		    }
//		}catch (DuplicateKeyException e) {
//	        // 중복 키 오류 발생 시
//	        redirectAttributes.addFlashAttribute("insertResult", "duplicate");
//	    } catch (Exception e) {
//	        // 기타 예외
//	    	System.out.println(e);
//	        redirectAttributes.addFlashAttribute("insertResult", "fail");
//	    }
//		return "redirect:/updateProject";
//	}
}