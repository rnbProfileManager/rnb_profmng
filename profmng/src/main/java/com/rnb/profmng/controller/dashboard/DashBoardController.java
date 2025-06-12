package com.rnb.profmng.controller.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rnb.profmng.dto.project.ProjectDTO;
import com.rnb.profmng.service.project.ProjectService;

@Controller
public class DashBoardController{
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/dashboard")
	public String showProjectPage(Model model) {
		List<ProjectDTO> result = projectService.allProjects();
		
		if(result.size() > 0) {
			result.sort((a, b) -> b.getStartDate().compareTo(a.getStartDate()));
			int idx = Math.min(result.size(), 4);
			int count = 0;
			
			for (ProjectDTO project : result) {
				if (count >= 4) break;
				
			    model.addAttribute("dashboard_projectCd" + idx, project.getProjectCd());
			    model.addAttribute("dashboard_projectNm" + idx, project.getProjectNm());
			    model.addAttribute("dashboard_startDate" + idx,
			            project.getStartDate() != null ? project.getStartDate() : null);
			    model.addAttribute("dashboard_endDate" + idx,
			            project.getEndDate() != null ? project.getEndDate() : "9999-12-31");
			    idx--;
			    count++;
			}
		}

		return "web/dashboard";
	}
}




