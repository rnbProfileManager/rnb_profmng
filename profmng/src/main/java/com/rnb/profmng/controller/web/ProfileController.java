package com.rnb.profmng.controller.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rnb.profmng.dto.ProfiledbDto;
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
	    @GetMapping("/download/{fileName}")
	    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
	        return azureStorageService.downloadFile(fileName);
	    }

	    /**
	     * 파일 리스트 조회 API
	     */
	    @GetMapping("/list")
	    public ResponseEntity<List<String>> listFiles() {
	        return azureStorageService.listFiles();
	    }

//	    // 화면 진입
//	    @GetMapping("/profile")
//	    public String showProjectPage() {
//	        return "web/profile";  // 여기 주의!!
//	    }
	    
	    
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
	
//	@GetMapping("/profile")
//	public String showProjectPage() {
//		return "web/profile";
//	}
	
	@GetMapping("/selectProfile")
	public String showSelectProfilePage() {
		return "web/selectProfile";
	}
	
	@GetMapping("/empProfileManage")
	public String showEmpProfileManagePage() {
		return "web/empProfileManage";
	}
	
	@GetMapping("/empProjectInsert")
	public String showEmpProjectInsertPage() {
		return "web/empProjectInsert";
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