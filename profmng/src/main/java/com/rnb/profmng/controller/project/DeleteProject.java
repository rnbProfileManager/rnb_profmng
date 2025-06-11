package com.rnb.profmng.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.service.ProjectService;

@Controller
public class DeleteProject{

	@Autowired
	private ProjectService projectService;

	@PostMapping("/deleteButton")
	public String deleteProject(@RequestParam String projectCd, RedirectAttributes redirectAttributes) {
		try {
			int result = projectService.deleteProject(projectCd);

		    if (result > 0) {
		        redirectAttributes.addFlashAttribute("deleteResult", "success");
		        return "redirect:/selectProject";
		    } else {
		        redirectAttributes.addFlashAttribute("deleteResult", "fail");
		        return "redirect:/updateProjectWithCd?projectCd=" + projectCd;
		    }
		}catch (DuplicateKeyException e) {
	        // 중복 키 오류 발생 시
	        redirectAttributes.addFlashAttribute("deleteResult", "duplicate");
	        return "redirect:/updateProjectWithCd?projectCd=" + projectCd;
	    } catch (Exception e) {
	        // 기타 예외
	        redirectAttributes.addFlashAttribute("deleteResult", "fail");
	        return "redirect:/updateProjectWithCd?projectCd=" + projectCd;
	    }
	}
}