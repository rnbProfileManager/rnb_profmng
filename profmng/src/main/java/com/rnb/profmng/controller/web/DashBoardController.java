package com.rnb.profmng.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.service.ProjectService;

@Controller
public class DashBoardController{
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/dashboard")
	public String showProjectPage(Model model) {
		List<ProjectDTO> result = projectService.selectProject();
		System.out.println(result);
		
		// DESC 정렬. 최신순으로 번호 역순
		ProjectDTO project1 = result.get(3);
		ProjectDTO project2 = result.get(2);
		ProjectDTO project3 = result.get(1);
		ProjectDTO project4 = result.get(0);
		
		model.addAttribute("dashboard_projectCd1", project1.getProjectCd());
		model.addAttribute("dashboard_projectNm1", project1.getProjectNm());
		model.addAttribute("dashboard_startDate1", project1.getStartDate());
		model.addAttribute("dashboard_endDate1", project1.getEndDate() == null ? "9999-12-31" : project4.getEndDate());

		model.addAttribute("dashboard_projectCd2", project2.getProjectCd());
		model.addAttribute("dashboard_projectNm2", project2.getProjectNm());
		model.addAttribute("dashboard_startDate2", project2.getStartDate());
		model.addAttribute("dashboard_endDate2", project2.getEndDate() == null ? "9999-12-31" : project4.getEndDate());

		model.addAttribute("dashboard_projectCd3", project3.getProjectCd());
		model.addAttribute("dashboard_projectNm3", project3.getProjectNm());
		model.addAttribute("dashboard_startDate3", project3.getStartDate());
		model.addAttribute("dashboard_endDate3", project3.getEndDate() == null ? "9999-12-31" : project4.getEndDate());

		model.addAttribute("dashboard_projectCd4", project4.getProjectCd());
		model.addAttribute("dashboard_projectNm4", project4.getProjectNm());
		model.addAttribute("dashboard_startDate4", project4.getStartDate());
		model.addAttribute("dashboard_endDate4", project4.getEndDate() == null ? "9999-12-31" : project4.getEndDate());

		return "web/dashboard";
	}
	

}




