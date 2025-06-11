package com.rnb.profmng.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.ProfmngApplication;
import com.rnb.profmng.controller.web.SystemController;
import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.service.ProjectService;

@Controller
public class InsertProject{

    private final ProfmngApplication profmngApplication;

    private final SystemController systemController;
	
	@Autowired
	private ProjectService projectService;

	InsertProject(SystemController systemController, ProfmngApplication profmngApplication) {
        this.systemController = systemController;
        this.profmngApplication = profmngApplication;
    }
	
	@GetMapping("/insertProject")
	public String showInsertProjectPage() {
		return "project/insertProject";
	}
	
    // 데이터 작업 form 매핑	
	@PostMapping("/insertProjectData")
	public String insertProject(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {
		try {
			Project result = projectService.insert(projectDto);
		    if (result != null) {
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