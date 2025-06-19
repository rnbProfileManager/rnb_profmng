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

import com.rnb.profmng.controller.dashboard.DashBoardController;
import com.rnb.profmng.dto.ProfiledbDto;
import com.rnb.profmng.dto.profile.EmpAbilityInfoDTO;
import com.rnb.profmng.dto.profile.EmpBasDTO;
import com.rnb.profmng.dto.profile.PjtHmnResrcInfoDTO;
import com.rnb.profmng.entity.profile.EmpAbilityInfo;
import com.rnb.profmng.entity.profile.EmpAbilityInfoPK;
import com.rnb.profmng.entity.profile.EmpBas;
import com.rnb.profmng.entity.profile.EmpBasPK;
import com.rnb.profmng.entity.profile.PjtHmnResrcInfo;
import com.rnb.profmng.entity.profile.PjtHmnResrcInfoPK;
import com.rnb.profmng.repository.profile.EmpAbilityInfoRepo;
import com.rnb.profmng.repository.profile.EmpBasRepo;
import com.rnb.profmng.repository.profile.PjtHmnResrcInfoRepo;
import com.rnb.profmng.service.AzureStorageService;
import com.rnb.profmng.service.ProfileService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProfileController{

    private final ProfileService profileService;
    private final EmpBasRepo empBasRepo;
    private final EmpAbilityInfoRepo empAbilityInfoRepo;
    private final PjtHmnResrcInfoRepo pjtHmnResrcInfoRepo;
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
            @RequestParam(required = false) String empId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return profileService.searchProfiles(empId, startDate, endDate);
    }
    
    /**
     * 파일 업로드 API
     * @throws Exception 
     */
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("empId") String empId,
            @RequestParam("empNm") String empNm,
            @RequestParam("file") MultipartFile file)  {
    	String text = "";
    	try {
            text = azureStorageService.uploadProfileFile(empId, empNm, file);
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
            @RequestParam(required = false) String empId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) {

        List<ProfiledbDto> employeeList = profileService.searchProfiles(empId, startDate, endDate);
        model.addAttribute("employeeList", employeeList);
        return "web/profile";
    }
	
	
	/*
	 * 직원 정보
	 */
	
    // 직원 정보 탭
    @GetMapping("/profile/empBas")
    public String allEmpBas(Model model) {
        List<EmpBasDTO> empBasList = profileService.allEmpBas();
        model.addAttribute("empBasList", empBasList);
        return "profile/empBas";
    }
	
    // 특정 직원 정보 조회
    @GetMapping("/profile/empBas/manage")
    public String selectempBas(@RequestParam("empNm") String empNm, Model model) {
        List<EmpBas> result = empBasRepo.findByEmpBasPk_EmpId(empNm);
        model.addAttribute("empBasList", result);
        return "profile/empBas";
    }
	
    // 신규 직원 정보 추가
    @GetMapping("/profile/addEmpBas")
    public String showAddempBasPage() {
        return "profile/addEmpBas";
    }
    
    @PostMapping("/profile/addEmpBas")
    public String addEmpBasPage(@ModelAttribute EmpBasDTO dto, RedirectAttributes redirectAttributes) {

        try {
            EmpBasPK pk = new EmpBasPK(
            		dto.getEmpId(),
            		dto.getEfctStartDate()
                );

            if (profileService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/profile/addEmpBas";
            }
        	
            profileService.saveEmpBas(dto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/profile/addEmpBas";
    }

    // 직원 정보 수정
    @GetMapping("/profile/editEmpBas")
    public String editEmpBasForm(
            @RequestParam("empId") String empId,
            @RequestParam("empNm") String empNm,
            @RequestParam("efctStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate efctStartDate,
            Model model) {

    	
    	EmpBasPK pk = new EmpBasPK(empId, efctStartDate);
        EmpBas empBas = profileService.findByPk(pk);
        model.addAttribute("empId", empId);
        model.addAttribute("empNm", empNm);
        model.addAttribute("efctStartDate", efctStartDate);
        return "profile/editEmpBas";
    }
    
    @PostMapping("/profile/editEmpBas")
    public String editEmpBasPage(@ModelAttribute EmpBasDTO dto, RedirectAttributes redirectAttributes) {
        try {
        	System.out.println(dto);
        	EmpBasPK pk = new EmpBasPK(
        			dto.getEmpId(),
        			dto.getEfctStartDate()
            );

        	EmpBas existingempBas = profileService.findByPk(pk);
        	System.out.println(existingempBas);
            profileService.updateEmpBasFromDto(dto, existingempBas);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/profile/editEmpBas?empId=" + URLEncoder.encode(dto.getEmpId(), StandardCharsets.UTF_8)
        	+ "&empNm=" + URLEncoder.encode(dto.getEmpNm(), StandardCharsets.UTF_8)
        	+ "&efctStartDate=" + dto.getEfctStartDate();
    }
    
    // 직원 정보 삭제
    @GetMapping("/profile/deleteEmpBas")
    public String deleteEmpBasPage(@RequestParam("empId") List<String> empIds, RedirectAttributes redirectAttributes) {
        try {
            for (String empId : empIds) {
            	profileService.deleteEmpBas(empId);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/profile/empBas";
    }
    
    
	
	/*
	 * 투입 관리
	 */
    
    // 투입 인력 탭
    @GetMapping("/profile/projectEmpInfo")
    public String allProfileInfo(Model model) {
        List<PjtHmnResrcInfoDTO> projectEmpInfoList = profileService.allPjtHmnResrcInfo();
        model.addAttribute("projectEmpInfoList", projectEmpInfoList);
        return "profile/projectEmpInfo";
    }
    
    // 특정 사원 투입 프로젝트 조회
    @GetMapping("/profile/projectEmpInfo/manage")
    public String selectProfileInfo(@RequestParam("empId") String empId, Model model) {
        List<PjtHmnResrcInfo> result = pjtHmnResrcInfoRepo.findByPjtHmnResrcInfoPk_EmpId(empId);
        model.addAttribute("projectEmpInfoList", result);
        return "profile/projectEmpInfo";
    }
    
    // 신규 투입 인력 추가
    @GetMapping("/profile/addProjectEmpInfo")
    public String showAddProjectEmpInfoPage() {
        return "profile/addProjectEmpInfo";
    }
	
    @PostMapping("/profile/addProjectEmpInfo")
    public String addProjectEmpInfoPage(@ModelAttribute PjtHmnResrcInfoDTO dto, RedirectAttributes redirectAttributes) {

        try {
            PjtHmnResrcInfoPK pk = new PjtHmnResrcInfoPK(
            		dto.getPjtSeq(),
            		dto.getEmpId(),
            		dto.getEfctStartDate()
                );

            if (profileService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/profile/addProjectEmpInfo";
            }
        	
            profileService.savePjtHmnResrcInfo(dto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/profile/addProjectEmpInfo";
    }

    // 투입 인력 수정
    @GetMapping("/profile/editProjectEmpInfo")
    public String editProjectEmpInfoForm(
            @RequestParam("pjtSeq") long pjtSeq,
            @RequestParam("empId") String empId,
            @RequestParam("efctStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate efctStartDate,
            Model model) {

    	
        PjtHmnResrcInfoPK pk = new PjtHmnResrcInfoPK(pjtSeq, empId, efctStartDate);
        PjtHmnResrcInfo projectEmpInfo = profileService.findByPk(pk);
        model.addAttribute("projectCd", pjtSeq);
        model.addAttribute("projectNm", empId);
        model.addAttribute("startDate", efctStartDate);
        return "profile/editProjectEmpInfo";
    }
    
    @PostMapping("/profile/editProjectEmpInfo")
    public String editProjectEmpInfoPage(@ModelAttribute PjtHmnResrcInfoDTO dto, RedirectAttributes redirectAttributes) {
        try {
        	PjtHmnResrcInfoPK pk = new PjtHmnResrcInfoPK(
        			dto.getPjtSeq(),
        			dto.getEmpId(),
        			dto.getEfctStartDate()
            );

        	PjtHmnResrcInfo existingProjectEmpInfo = profileService.findByPk(pk);

            profileService.updatePjtHmnResrcInfoFromDto(dto, existingProjectEmpInfo);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/profile/editProjectEmpInfo?projectCd=" + dto.getPjtSeq()
             + "&empId=" + URLEncoder.encode(dto.getEmpId(), StandardCharsets.UTF_8)
             + "&startDate=" + dto.getEfctStartDate();
    }
    
    // 투입 인력 삭제
    @GetMapping("/profile/deleteProjectEmpInfo")
    public String deleteProjectEmpInfoPage(@RequestParam("empId") List<String> pjtSeqs, RedirectAttributes redirectAttributes) {
        try {
//            for (String pjtSeq : pjtSeqs) {
//                profileService.deletePjtHmnResrcInfo(pjtSeq);
//            }
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
        List<EmpAbilityInfoDTO> empAbilityList = profileService.allEmpAbilityInfo();
        model.addAttribute("empAbilityList", empAbilityList);
        return "profile/empAbility";
    }
    
    // 특정 사원 직무 능력 조회
    @GetMapping("/profile/empAbility/manage")
    public String selectEmpAbility(@RequestParam("empId") String empId, Model model) {
        List<EmpAbilityInfo> result = empAbilityInfoRepo.findByEmpAbilityInfoPk_EmpId(empId);
        model.addAttribute("empAbilityList", result);
        return "profile/empAbility";
    }
	
    // 신규 직무 능력 추가
    @GetMapping("/profile/addEmpAbility")
    public String showAddEmpAbilityPage() {
        return "profile/addEmpAbility";
    }
	
    @PostMapping("/profile/addEmpAbility")
    public String addEmpAbilityPage(@ModelAttribute EmpAbilityInfoDTO dto, RedirectAttributes redirectAttributes) {

        try {
            EmpAbilityInfoPK pk = new EmpAbilityInfoPK(
            		dto.getEmpId(),
            		dto.getAbilityNm()
                );

            if (profileService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/profile/addEmpAbility";
            }
        	
            profileService.saveEmpAbilityInfo(dto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/profile/addEmpAbility";
    }

    // 직무 능력 수정
    @GetMapping("/profile/editEmpAbility")
    public String editEmpAbilityForm(
            @RequestParam("empId") String empId,
            //@RequestParam("empNm") String empNm,
            @RequestParam("abilityType") String abilityType,
            @RequestParam("abilityNm") String abilityNm,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            Model model) {

    	
    	EmpAbilityInfoPK pk = new EmpAbilityInfoPK(empId, abilityNm);
    	EmpAbilityInfo empAbility = profileService.findByPk(pk);
        model.addAttribute("empId", empId);
        model.addAttribute("abilityNm", abilityNm);
        
        return "profile/editEmpAbility";
    }
    
    @PostMapping("/profile/editEmpAbility")
    public String editEmpAbilityPage(@ModelAttribute EmpAbilityInfoDTO dto, RedirectAttributes redirectAttributes) {
        try {
        	EmpAbilityInfoPK pk = new EmpAbilityInfoPK(
        			dto.getEmpId(),
        			dto.getAbilityNm()
            );

        	EmpAbilityInfo existingEmpAbility = profileService.findByPk(pk);

            profileService.updateEmpAbilityInfoFromDto(dto, existingEmpAbility);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/profile/editEmpAbility?empId=" + URLEncoder.encode(dto.getEmpId(), StandardCharsets.UTF_8)
             + "&abilityNm=" + URLEncoder.encode(dto.getAbilityNm(), StandardCharsets.UTF_8);
    }
    
    // 직무 능력 삭제
    @GetMapping("/profile/deleteEmpAbility")
    public String deleteEmpAbilityPage(@RequestParam("empId") List<String> empIds, RedirectAttributes redirectAttributes) {
        try {
            for (String empId : empIds) {
//                profileService.deleteEmpAbilityInfo(empId);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/profile/empAbility";
    }
}



