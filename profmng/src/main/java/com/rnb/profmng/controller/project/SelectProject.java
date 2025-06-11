package com.rnb.profmng.controller.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.service.ProjectService;

@Controller
public class SelectProject{

	@Autowired
	private ProjectService projectService;

	@GetMapping("/selectProject")
	public String showSelectProjectPage() {
		return "project/selectProject";
	}
	
	@PostMapping("/selectProjectData")
	public String selectProject(String projectCd, RedirectAttributes redirectAttributes) {
		try {
			List<ProjectDTO> result = projectService.select(projectCd);
		    if (result != null) {
		    	System.out.println("success");
		    	ProjectDTO projectDto = (ProjectDTO) result.get(0);
		        redirectAttributes.addFlashAttribute("selectResult", "success");
		        redirectAttributes.addFlashAttribute("projectCd", projectDto.getProjectCd());
		        redirectAttributes.addFlashAttribute("projectNm", projectDto.getProjectNm());
		        redirectAttributes.addFlashAttribute("startDate", projectDto.getStartDate());
		        redirectAttributes.addFlashAttribute("endDate", projectDto.getEndDate());
		        redirectAttributes.addFlashAttribute("pmId", projectDto.getPmId());
		        redirectAttributes.addFlashAttribute("client", projectDto.getClient());
		        redirectAttributes.addFlashAttribute("contractor", projectDto.getContractor());
		        redirectAttributes.addFlashAttribute("manMonth", projectDto.getManMonth());
		        redirectAttributes.addFlashAttribute("totAmt", projectDto.getTotAmt());
		        redirectAttributes.addFlashAttribute("projectType", projectDto.getProjectType());
		    } else {
		        redirectAttributes.addFlashAttribute("selectResult", "no Data");
		    }
		}catch (DuplicateKeyException e) {
	        // 중복 키 오류 발생 시
	        redirectAttributes.addFlashAttribute("selectResult", "duplicate");
	    } catch (Exception e) {
	        // 기타 예외
	        redirectAttributes.addFlashAttribute("selectResult", "fail");
	    }
		return "redirect:/selectProject";
	}
}