package com.rnb.profmng.controller.web;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.dto.ProfiledbDto;
import com.rnb.profmng.dto.profile.EmpAbilityDTO;
import com.rnb.profmng.dto.profile.EmpNoDTO;
import com.rnb.profmng.dto.profile.ProjectEmpInfoDTO;
import com.rnb.profmng.entity.profile.EmpAbility;
import com.rnb.profmng.entity.profile.EmpAbilityPK;
import com.rnb.profmng.entity.profile.EmpNo;
import com.rnb.profmng.entity.profile.EmpNoPK;
import com.rnb.profmng.entity.profile.ProjectEmpInfo;
import com.rnb.profmng.entity.profile.ProjectEmpInfoPK;
import com.rnb.profmng.repository.profile.EmpAbilityRepo;
import com.rnb.profmng.repository.profile.EmpNoRepo;
import com.rnb.profmng.repository.profile.ProjectEmpInfoRepo;
import com.rnb.profmng.service.AzureStorageService;
import com.rnb.profmng.service.ProfileService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProfileController{
	
	@Autowired
    private final ProfileService profileService;
	    
    @Autowired
    private final EmpNoRepo empNoRepo;
	    
    @Autowired
    private final EmpAbilityRepo empAbilityRepo;
	    
    @Autowired
    private final ProjectEmpInfoRepo projectEmpInfoRepo;

    private final AzureStorageService azureStorageService;

    
    /*
     * 파일 업로드 - azure
     */
    
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
	
    // 직원 정보 탭
    @GetMapping("/profile/empNo")
    public String allEmpNo(Model model) {
        List<EmpNoDTO> empNoList = profileService.allEmpInfos();
        model.addAttribute("empNoList", empNoList);
        return "profile/empNo";
    }
	
    // 특정 직원 정보 조회
    @GetMapping("/profile/empNo/manage")
    public String selectEmpNo(@RequestParam("empCd") String empCd, Model model) {
        List<EmpNo> result = empNoRepo.findByEmpNoPk_EmpCd(empCd);
        model.addAttribute("empNoList", result);
        return "profile/empNo";
    }
	
    // 신규 직원 정보 추가
    @GetMapping("/profile/addEmpNo")
    public String showAddEmpNoPage() {
        return "profile/addEmpNo";
    }
    
    @PostMapping("/profile/addEmpNo")
    public String addEmpNoPage(@ModelAttribute EmpNoDTO empNoDto, RedirectAttributes redirectAttributes) {

        try {
            EmpNoPK pk = new EmpNoPK(
            		empNoDto.getEmpCd(),
            		empNoDto.getEmpNm(),
            		empNoDto.getStartDate()
                );

            if (profileService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/profile/addEmpNo";
            }
        	
            profileService.saveEmpNo(empNoDto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/profile/addEmpNo";
    }

    // 직원 정보 수정
    @GetMapping("/profile/editEmpNo")
    public String editEmpNoForm(
            @RequestParam("empCd") String empCd,
            @RequestParam("empNm") String empNm,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            Model model) {

    	
    	EmpNoPK pk = new EmpNoPK(empCd, empNm, startDate);
        EmpNo empNo = profileService.findByPk(pk);
        model.addAttribute("empCd", empCd);
        model.addAttribute("empNm", empNm);
        model.addAttribute("startDate", startDate);
        return "profile/editEmpNo";
    }
    
    @PostMapping("/profile/editEmpNo")
    public String editEmpNoPage(@ModelAttribute EmpNoDTO empNoDto, RedirectAttributes redirectAttributes) {
        try {
        	EmpNoPK pk = new EmpNoPK(
        			empNoDto.getEmpCd(),
        			empNoDto.getEmpNm(),
        			empNoDto.getStartDate()
            );

        	EmpNo existingEmpNo = profileService.findByPk(pk);

            profileService.updateEmpNoFromDto(empNoDto, existingEmpNo);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/profile/editEmpNo?empCd=" + URLEncoder.encode(empNoDto.getEmpCd(), StandardCharsets.UTF_8)
             + "&empNm=" + URLEncoder.encode(empNoDto.getEmpNm(), StandardCharsets.UTF_8)
             + "&startDate=" + empNoDto.getStartDate();
    }
    
    // 직원 정보 삭제
    @GetMapping("/profile/deleteEmpNo")
    public String deleteEmpNoPage(@RequestParam("empCd") List<String> empCds, RedirectAttributes redirectAttributes) {
        try {
            for (String empCd : empCds) {
            	profileService.deleteEmpNo(empCd);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/profile/empNo";
    }
    
    
	
	/*
	 * 투입 관리
	 */
    
    // 투입 인력 탭
    @GetMapping("/profile/projectEmpInfo")
    public String allProfileInfo(Model model) {
        List<ProjectEmpInfoDTO> projectEmpInfoList = profileService.allProjectInfos();
        model.addAttribute("projectEmpInfoList", projectEmpInfoList);
        return "profile/projectEmpInfo";
    }
    
    // 특정 사원 투입 프로젝트 조회
    @GetMapping("/profile/projectEmpInfo/manage")
    public String selectProfileInfo(@RequestParam("empCd") String empCd, Model model) {
        List<ProjectEmpInfo> result = projectEmpInfoRepo.findByProjectEmpInfoPk_EmpCd(empCd);
        model.addAttribute("projectEmpInfoList", result);
        return "profile/projectEmpInfo";
    }
    
    // 신규 투입 인력 추가
    @GetMapping("/profile/addProjectEmpInfo")
    public String showAddProjectEmpInfoPage() {
        return "profile/addProjectEmpInfo";
    }
	
    @PostMapping("/profile/addProjectEmpInfo")
    public String addProjectEmpInfoPage(@ModelAttribute ProjectEmpInfoDTO projectEmpInfoDto, RedirectAttributes redirectAttributes) {

        try {
            ProjectEmpInfoPK pk = new ProjectEmpInfoPK(
            		projectEmpInfoDto.getProjectCd(),
            		projectEmpInfoDto.getEmpCd(),
            		projectEmpInfoDto.getProjectNm(),
            		projectEmpInfoDto.getStartDate()
                );

            if (profileService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/profile/addProjectEmpInfo";
            }
        	
            profileService.saveProjectEmpInfo(projectEmpInfoDto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/profile/addProjectEmpInfo";
    }

    // 투입 인력 수정
    @GetMapping("/profile/editProjectEmpInfo")
    public String editProjectEmpInfoForm(
            @RequestParam("projectCd") String projectCd,
            @RequestParam("empCd") String empCd,
            @RequestParam("projectNm") String projectNm,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            Model model) {

    	
        ProjectEmpInfoPK pk = new ProjectEmpInfoPK(projectCd, empCd, projectNm, startDate);
        ProjectEmpInfo projectEmpInfo = profileService.findByPk(pk);
        model.addAttribute("projectCd", projectCd);
        model.addAttribute("empCd", empCd);
        model.addAttribute("projectNm", projectNm);
        model.addAttribute("startDate", startDate);
        return "profile/editProjectEmpInfo";
    }
    
    @PostMapping("/profile/editProjectEmpInfo")
    public String editProjectEmpInfoPage(@ModelAttribute ProjectEmpInfoDTO projectEmpInfoDto, RedirectAttributes redirectAttributes) {
        try {
        	ProjectEmpInfoPK pk = new ProjectEmpInfoPK(
        			projectEmpInfoDto.getProjectCd(),
        			projectEmpInfoDto.getEmpCd(),
        			projectEmpInfoDto.getProjectNm(),
        			projectEmpInfoDto.getStartDate()
            );

        	ProjectEmpInfo existingProjectEmpInfo = profileService.findByPk(pk);

            profileService.updateProjectEmpInfoFromDto(projectEmpInfoDto, existingProjectEmpInfo);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/profile/editProjectEmpInfo?projectCd=" + URLEncoder.encode(projectEmpInfoDto.getProjectCd(), StandardCharsets.UTF_8)
             + "&empCd=" + URLEncoder.encode(projectEmpInfoDto.getEmpCd(), StandardCharsets.UTF_8)
             + "&projectNm=" + URLEncoder.encode(projectEmpInfoDto.getProjectNm(), StandardCharsets.UTF_8)
             + "&startDate=" + projectEmpInfoDto.getStartDate();
    }
    
    // 투입 인력 삭제
    @GetMapping("/profile/deleteProjectEmpInfo")
    public String deleteProjectEmpInfoPage(@RequestParam("empCd") List<String> empCds, RedirectAttributes redirectAttributes) {
        try {
            for (String empCd : empCds) {
                profileService.deleteProjectEmpInfo(empCd);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/profile/projectEmpInfo";
    }
	
    
    
    
	
	/*
	 * 직무 능력
	 */
    
    // 직무 능력 탭
    @GetMapping("/profile/empAbility")
    public String allEmpAbility(Model model) {
        List<EmpAbilityDTO> empAbilityList = profileService.allEmpAbilitys();
        model.addAttribute("empAbilityList", empAbilityList);
        return "profile/empAbility";
    }
    
    // 특정 사원 직무 능력 조회
    @GetMapping("/profile/empAbility/manage")
    public String selectEmpAbility(@RequestParam("empCd") String empCd, Model model) {
        List<EmpAbility> result = empAbilityRepo.findByEmpAbilityPk_EmpCd(empCd);
        model.addAttribute("empAbilityList", result);
        return "profile/empAbility";
    }
	
    // 신규 직무 능력 추가
    @GetMapping("/profile/addEmpAbility")
    public String showAddEmpAbilityPage() {
        return "profile/addEmpAbility";
    }
	
    @PostMapping("/profile/addEmpAbility")
    public String addEmpAbilityPage(@ModelAttribute EmpAbilityDTO empAbilityDto, RedirectAttributes redirectAttributes) {

        try {
            EmpAbilityPK pk = new EmpAbilityPK(
            		empAbilityDto.getEmpCd(),
            		empAbilityDto.getEmpNm(),
            		empAbilityDto.getAbilityType(),
            		empAbilityDto.getAbilityNm(),
            		empAbilityDto.getStartDate()
                );

            if (profileService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/profile/addEmpAbility";
            }
        	
            profileService.saveEmpAbility(empAbilityDto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/profile/addEmpAbility";
    }

    // 직무 능력 수정
    @GetMapping("/profile/editEmpAbility")
    public String editEmpAbilityForm(
            @RequestParam("empCd") String empCd,
            @RequestParam("empNm") String empNm,
            @RequestParam("abilityType") String abilityType,
            @RequestParam("abilityNm") String abilityNm,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            Model model) {

    	
    	EmpAbilityPK pk = new EmpAbilityPK(empCd, empNm, abilityType, abilityNm, startDate);
    	EmpAbility empAbility = profileService.findByPk(pk);
        model.addAttribute("empCd", empCd);
        model.addAttribute("empNm", empNm);
        model.addAttribute("abilityType", abilityType);
        model.addAttribute("abilityNm", abilityNm);
        model.addAttribute("startDate", startDate);
        return "profile/editEmpAbility";
    }
    
    @PostMapping("/profile/editEmpAbility")
    public String editEmpAbilityPage(@ModelAttribute EmpAbilityDTO empAbilityDto, RedirectAttributes redirectAttributes) {
        try {
        	EmpAbilityPK pk = new EmpAbilityPK(
        			empAbilityDto.getEmpCd(),
        			empAbilityDto.getEmpNm(),
        			empAbilityDto.getAbilityType(),
        			empAbilityDto.getAbilityNm(),
        			empAbilityDto.getStartDate()
            );

        	EmpAbility existingEmpAbility = profileService.findByPk(pk);

            profileService.updateEmpAbilityFromDto(empAbilityDto, existingEmpAbility);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/profile/editEmpAbility?empCd=" + URLEncoder.encode(empAbilityDto.getEmpCd(), StandardCharsets.UTF_8)
             + "&empNm=" + URLEncoder.encode(empAbilityDto.getEmpNm(), StandardCharsets.UTF_8)
             + "&abilityType=" + URLEncoder.encode(empAbilityDto.getAbilityType(), StandardCharsets.UTF_8)
             + "&abilityNm=" + URLEncoder.encode(empAbilityDto.getAbilityNm(), StandardCharsets.UTF_8)
             + "&startDate=" + empAbilityDto.getStartDate();
    }
    
    // 직무 능력 삭제
    @GetMapping("/profile/deleteEmpAbility")
    public String deleteEmpAbilityPage(@RequestParam("empCd") List<String> empCds, RedirectAttributes redirectAttributes) {
        try {
            for (String empCd : empCds) {
                profileService.deleteEmpAbility(empCd);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/profile/empAbility";
    }
}



