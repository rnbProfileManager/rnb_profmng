package com.rnb.profmng.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.rnb.profmng.ProfmngApplication;
import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.service.ProjectService;

@Controller
public class ProjectController{

    private final ProfmngApplication profmngApplication;

    private final SystemController systemController;
	
	@Autowired
	private ProjectService projectService;

    ProjectController(SystemController systemController, ProfmngApplication profmngApplication) {
        this.systemController = systemController;
        this.profmngApplication = profmngApplication;
    }
	
    // Web 페이지 변경 매핑
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
	
	
    // 데이터 작업 form 매핑	
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
	
	@PostMapping("/selectData")
	public String selectProject(String projectCd, RedirectAttributes redirectAttributes) {
		System.out.println(projectCd);
		try {
			List result = projectService.selectProject(projectCd);
			System.out.println(result);

		    if (result.size() > 0) {
		    	System.out.println("success");
		    	ProjectDTO projectDto = (ProjectDTO) result.get(0);
		        redirectAttributes.addFlashAttribute("insertResult", "success");
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
		    	System.out.println("no Data");
		        redirectAttributes.addFlashAttribute("insertResult", "no Data");
		    }
		}catch (DuplicateKeyException e) {
	        // 중복 키 오류 발생 시
			System.out.println("duplicate");
	        redirectAttributes.addFlashAttribute("insertResult", "duplicate");
	    } catch (Exception e) {
	        // 기타 예외
	    	System.out.println(e);
	    	System.out.println("fail");
	        redirectAttributes.addFlashAttribute("insertResult", "fail");
	    }
		return "redirect:/selectProject";
	}
	
	@PostMapping("/updateData")
	public String updateProject(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {
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
	
	@PostMapping("/deleteData")
	public String deleteProject(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {
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