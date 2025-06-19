package com.rnb.profmng.controller.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rnb.profmng.dto.project.ProjectDTO;
import com.rnb.profmng.repository.profile.PjtHmnResrcInfoRepo;
import com.rnb.profmng.service.project.ProjectService;

@Controller
public class DashBoardController{

    private final PjtHmnResrcInfoRepo pjtHmnResrcInfoRepo;
	
	@Autowired
	private ProjectService projectService;

    DashBoardController(PjtHmnResrcInfoRepo pjtHmnResrcInfoRepo) {
        this.pjtHmnResrcInfoRepo = pjtHmnResrcInfoRepo;
    }
	
	@GetMapping("/dashboard")
	public String showProjectPage(Model model) {
		List<ProjectDTO> result = projectService.allProjects();
		
		if(result.size() > 0) {
			result.sort((a, b) -> b.getEfctStartDate().compareTo(a.getEfctStartDate()));
			int idx = Math.min(result.size(), 4);
			int count = 0;
			
			for (ProjectDTO project : result) {
				if (count >= 4) break;
				
			    model.addAttribute("dashboard_projectCd" + idx, project.getPjtSeq());
			    model.addAttribute("dashboard_projectNm" + idx, project.getPjtNm());
			    model.addAttribute("dashboard_startDate" + idx,
			            project.getEfctStartDate() != null ? project.getEfctStartDate() : null);
			    model.addAttribute("dashboard_endDate" + idx,
			            project.getEfctEndDate() != null ? project.getEfctEndDate() : "9999-12-31");
			    idx--;
			    count++;
			}
		}

		return "web/dashboard";
	}
}




