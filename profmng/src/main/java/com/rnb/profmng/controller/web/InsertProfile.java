package com.rnb.profmng.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.dto.ProfileDTO;
import com.rnb.profmng.entity.profile.EmpAbility;
import com.rnb.profmng.entity.profile.EmpNo;
import com.rnb.profmng.entity.profile.ProjectEmpInfo;
import com.rnb.profmng.repository.ProjectEmpInfoRepo;
import com.rnb.profmng.service.ProfileService;

@Controller
public class InsertProfile{

    private final ProjectEmpInfoRepo projectEmpInfoRepo;

	@Autowired
	private ProfileService profileService;

    InsertProfile(ProjectEmpInfoRepo projectEmpInfoRepo) {
        this.projectEmpInfoRepo = projectEmpInfoRepo;
    }

	@GetMapping("/empProfileInsert")
	public String showInsertProfilePage() {
		return "web/empProfileInsert";
	}
	
    // 데이터 작업 form 매핑	
	@PostMapping("/insertProfileData")
	public String insertEmpNo(@ModelAttribute ProfileDTO profileDto, RedirectAttributes redirectAttributes) {
		try {
			EmpNo result = profileService.insertEmpNo(profileDto);
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
		return "redirect:/empProfileInsert";
	}
	
    // 데이터 작업 form 매핑	
	@PostMapping("/insertEmpAbilityData")
	public String insertEmpAbility(@ModelAttribute ProfileDTO profileDto, RedirectAttributes redirectAttributes) {
		try {
			EmpAbility result = profileService.insertEmpAbility(profileDto);
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
		return "redirect:/empAbilityInsert";
	}
	
    // 데이터 작업 form 매핑	
	@PostMapping("/insertProjectEmpInfoData")
	public String insertProjectEmpInfo(@ModelAttribute ProfileDTO profileDto, RedirectAttributes redirectAttributes) {
		System.out.println(profileDto);
		try {
			ProjectEmpInfo result = profileService.insertProjectEmpNo(profileDto);
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
		return "redirect:/empProjectInsert";
	}
}