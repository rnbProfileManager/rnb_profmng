package com.rnb.profmng.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.dto.profile.ProfileDTO;
import com.rnb.profmng.entity.profile.EmpAbility;
import com.rnb.profmng.entity.profile.EmpNo;
import com.rnb.profmng.entity.profile.ProjectEmpInfo;
import com.rnb.profmng.repository.profile.ProjectEmpInfoRepo;
import com.rnb.profmng.service.ProfileService;

@Controller
public class InsertProfile{

    private final ProjectEmpInfoRepo projectEmpInfoRepo;

	@Autowired
	private ProfileService profileInsertService;

    InsertProfile(ProjectEmpInfoRepo projectEmpInfoRepo) {
        this.projectEmpInfoRepo = projectEmpInfoRepo;
    }

	@GetMapping("/empProfileInsert")
	public String showInsertProfilePage() {
		return "web/empProfileInsert";
	}
	
	@PostMapping("/insertProfileData")
	public String insertEmpNo(@ModelAttribute ProfileDTO profileDto, RedirectAttributes redirectAttributes) {
		try {
			EmpNo result = profileInsertService.insertEmpNo(profileDto);
		    if (result != null) {
		        redirectAttributes.addFlashAttribute("insertResult", "success");
		    } else {
		        redirectAttributes.addFlashAttribute("insertResult", "fail");
		    }
		}catch (DuplicateKeyException e) {
	        redirectAttributes.addFlashAttribute("insertResult", "duplicate");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("insertResult", "fail");
	    }
		return "redirect:/empProfileInsert";
	}
	
	@PostMapping("/insertEmpAbilityData")
	public String insertEmpAbility(@ModelAttribute ProfileDTO profileDto, RedirectAttributes redirectAttributes) {
		try {
			EmpAbility result = profileInsertService.insertEmpAbility(profileDto);
		    if (result != null) {
		        redirectAttributes.addFlashAttribute("insertResult", "success");
		    } else {
		        redirectAttributes.addFlashAttribute("insertResult", "fail");
		    }
		}catch (DuplicateKeyException e) {
	        redirectAttributes.addFlashAttribute("insertResult", "duplicate");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("insertResult", "fail");
	    }
		return "redirect:/empAbilityInsert";
	}
	
	@PostMapping("/insertProjectEmpInfoData")
	public String insertProjectEmpInfo(@ModelAttribute ProfileDTO profileDto, RedirectAttributes redirectAttributes) {
		try {
			ProjectEmpInfo result = profileInsertService.insertProjectEmpNo(profileDto);
		    if (result != null) {
		        redirectAttributes.addFlashAttribute("insertResult", "success");
		    } else {
		        redirectAttributes.addFlashAttribute("insertResult", "fail");
		    }
		}catch (DuplicateKeyException e) {
	        redirectAttributes.addFlashAttribute("insertResult", "duplicate");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("insertResult", "fail");
	    }
		return "redirect:/empProjectInsert";
	}
}