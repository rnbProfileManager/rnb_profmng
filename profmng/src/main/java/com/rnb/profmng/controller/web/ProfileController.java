package com.rnb.profmng.controller.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rnb.profmng.dto.ProfiledbDto;
import com.rnb.profmng.dto.profile.EmpNoDTO;
import com.rnb.profmng.service.AzureStorageService;
import com.rnb.profmng.service.ProfileService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProfileController{
	
	    private final ProfileService profileService;
	    private final AzureStorageService azureStorageService;

	    // API용 전체조회
	    @GetMapping("/api/profiles")
	    public List<ProfiledbDto> getProfiles() {
	        return profileService.getAllProfiles();
	    }

	    // API용 검색
	    @GetMapping("/api/profiles/search")
	    public List<ProfiledbDto> searchProfiles(
	            @RequestParam(required = false) String empNm,
	            @RequestParam(required = false) String startDate,
	            @RequestParam(required = false) String endDate) {
	        return profileService.searchProfiles(empNm, startDate, endDate);
	    }
	    
	    /**
	     * 파일 업로드 API
	     * @throws Exception 
	     */
	    @PostMapping("/upload")
	    @ResponseBody
	    public String uploadFile(@RequestParam("empCd") String empCd,
	            @RequestParam("empNm") String empNm,
	            @RequestParam("file") MultipartFile file)  {
	    	String text = "";
	    	try {
	            text = azureStorageService.uploadProfileFile(empCd, empNm, file);
	            if (text == null) {
	                return "OK";
	            } else {
	                return text;
	            }
	        } catch (Exception e) {
	            return "ERROR: " + e.getMessage();
	        }
	    }

	    /**
	     * 파일 다운로드 API
	     */
	    @GetMapping("/download")
	    public ResponseEntity<byte[]> downloadFile(@RequestParam("file") String fileName) {
	        return azureStorageService.downloadFile(fileName);
	    }

	    /**
	     * 파일 리스트 조회 API
	     */
	    @GetMapping("/list")
	    public ResponseEntity<List<String>> listFiles() {
	        return azureStorageService.listFiles();
	    }

	    @GetMapping("/profile")
	    public String profile(Model model) {
	        List<ProfiledbDto> employeeList = profileService.getAllProfiles();
	        model.addAttribute("employeeList", employeeList);
	        return "web/profile";
	    }
	    

	    // 화면 진입 + 검색
	    @GetMapping("/profile/manage")
	    public String manage(
	            @RequestParam(required = false) String empNm,
	            @RequestParam(required = false) String startDate,
	            @RequestParam(required = false) String endDate,
	            Model model) {

	        List<ProfiledbDto> employeeList = profileService.searchProfiles(empNm, startDate, endDate);
	        model.addAttribute("employeeList", employeeList);
	        return "web/profile";
	    }
	
	
	/*
	 * 직원 정보
	 */
//	@GetMapping("/profile/empCd")
//	public String showSelectProfilePage() {
//		return "profile/empInfo";
//	}
	
    // 프로젝트 탭
    @GetMapping("/profile/empCd")
    public String allEmpInfo(Model model) {
        List<EmpNoDTO> profileList = profileService.allProfiles();
        model.addAttribute("profileList", profileList);
        return "profile/empNo";
    }
	
	
	
	/*
	 * 투입 관리
	 */
	@GetMapping("/profile/projectEmpCd")
	public String showEmpProfileManagePage() {
		return "profile/projectEmpInfo";
	}
	
	
	
	
	/*
	 * 직무 능력
	 */
	@GetMapping("/profile/empAbility")
	public String showEmpProjectInsertPage() {
		return "profile/empAbility";
	}
	
	
	
	
	
	@GetMapping("/empProjectManage")
	public String showEmpProjectManagePage() {
		return "web/empProjectManage";
	}
	
	@GetMapping("/empAbilityInsert")
	public String showEmpAbilityInserttPage() {
		return "web/empAbilityInsert";
	}
	
	@GetMapping("/empAbilityManage")
	public String showEmpAbilityManagePage() {
		return "web/empAbilityManage";
	}
}