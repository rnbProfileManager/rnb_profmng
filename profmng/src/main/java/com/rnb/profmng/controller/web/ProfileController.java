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
    public List<ProfiledbDto> searchProfiles(@RequestParam(required = false) String empId) {
        return profileService.searchProfiles(empId);
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
        return "profile/profile";
    }
    

    // 화면 진입 + 검색
    @GetMapping("/profile/manage")
    public String manage(
            @RequestParam(required = false) String empId,
            Model model) {

        List<ProfiledbDto> employeeList = profileService.searchProfiles(empId);
        model.addAttribute("employeeList", employeeList);
        return "profile/profile";
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
    @GetMapping("/profile/pjtHmnResrcInfo")
    public String allProfileInfo(Model model) {
        List<PjtHmnResrcInfoDTO> pjtHmnResrcInfoList = profileService.allPjtHmnResrcInfo();
        model.addAttribute("pjtHmnResrcInfoList", pjtHmnResrcInfoList);
        return "profile/pjtHmnResrcInfo";
    }
    
    // 특정 사원 투입 프로젝트 조회
    @GetMapping("/profile/pjtHmnResrcInfo/manage")
    public String selectProfileInfo(@RequestParam("empId") String empId, Model model) {
        List<PjtHmnResrcInfo> result = pjtHmnResrcInfoRepo.findByPjtHmnResrcInfoPk_EmpId(empId);
        model.addAttribute("pjtHmnResrcInfoList", result);
        return "profile/pjtHmnResrcInfo";
    }
    
    // 신규 투입 인력 추가
    @GetMapping("/profile/addPjtHmnResrcInfo")
    public String showAddPjtHmnResrcInfoPage() {
        return "profile/addPjtHmnResrcInfo";
    }
	
    @PostMapping("/profile/addPjtHmnResrcInfo")
    public String addPjtHmnResrcInfoPage(@ModelAttribute PjtHmnResrcInfoDTO dto, RedirectAttributes redirectAttributes) {

        try {
            PjtHmnResrcInfoPK pk = new PjtHmnResrcInfoPK(
            		dto.getPjtSeq(),
            		dto.getEmpId(),
            		dto.getEfctStartDate()
                );

            if (profileService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/profile/addPjtHmnResrcInfo";
            }
        	
            profileService.savePjtHmnResrcInfo(dto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/profile/addPjtHmnResrcInfo";
    }

    // 투입 인력 수정
    @GetMapping("/profile/editPjtHmnResrcInfo")
    public String editPjtHmnResrcInfoForm(
            @RequestParam("pjtSeq") long pjtSeq,
            @RequestParam("empId") String empId,
            @RequestParam("efctStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate efctStartDate,
            Model model) {

        PjtHmnResrcInfoPK pk = new PjtHmnResrcInfoPK(pjtSeq, empId, efctStartDate);
        PjtHmnResrcInfo pjtHmnResrcInfo = profileService.findByPk(pk);
        
        model.addAttribute("pjtSeq", pjtSeq);
        model.addAttribute("empId", empId);
        model.addAttribute("efctStartDate", efctStartDate);
        return "profile/editPjtHmnResrcInfo";
    }
    
    @PostMapping("/profile/editPjtHmnResrcInfo")
    public String editPjtHmnResrcInfoPage(@ModelAttribute PjtHmnResrcInfoDTO dto, RedirectAttributes redirectAttributes) {
        try {
        	System.out.println(dto);
        	PjtHmnResrcInfoPK pk = new PjtHmnResrcInfoPK(
        			dto.getPjtSeq(),
        			dto.getEmpId(),
        			dto.getEfctStartDate()
            );

        	PjtHmnResrcInfo existingPjtHmnResrcInfo = profileService.findByPk(pk);

            profileService.updatePjtHmnResrcInfoFromDto(dto, existingPjtHmnResrcInfo);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/profile/editPjtHmnResrcInfo?pjtSeq=" + dto.getPjtSeq()
             + "&empId=" + URLEncoder.encode(dto.getEmpId(), StandardCharsets.UTF_8)
             + "&efctStartDate=" + dto.getEfctStartDate();
    }
    
    // 투입 인력 삭제
    @GetMapping("/profile/deletePjtHmnResrcInfo")
    public String deletePjtHmnResrcInfoPage(@RequestParam("empId") List<String> pjtSeqs, RedirectAttributes redirectAttributes) {
        System.out.println(pjtSeqs);
    	try {
            for (String pjtSeq : pjtSeqs) {
                profileService.deletePjtHmnResrcInfo(pjtSeq);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/profile/pjtHmnResrcInfo";
    }
	
    
    
    
	
	/*
	 * 직무 능력
	 */
    
    // 직무 능력 탭
    @GetMapping("/profile/empAbilityInfo")
    public String allEmpAbilityInfo(Model model) {
        List<EmpAbilityInfoDTO> empAbilityInfoList = profileService.allEmpAbilityInfo();
        model.addAttribute("empAbilityInfoList", empAbilityInfoList);
        return "profile/empAbilityInfo";
    }
    
    // 특정 사원 직무 능력 조회
    @GetMapping("/profile/empAbilityInfo/manage")
    public String selectEmpAbilityInfo(@RequestParam("empId") String empId, Model model) {
        List<EmpAbilityInfo> result = empAbilityInfoRepo.findByEmpAbilityInfoPk_EmpId(empId);
        model.addAttribute("empAbilityInfoList", result);
        return "profile/empAbilityInfo";
    }
	
    // 신규 직무 능력 추가
    @GetMapping("/profile/addEmpAbilityInfo")
    public String showAddEmpAbilityInfoPage() {
        return "profile/addEmpAbilityInfo";
    }
	
    @PostMapping("/profile/addEmpAbilityInfo")
    public String addEmpAbilityInfoPage(@ModelAttribute EmpAbilityInfoDTO dto, RedirectAttributes redirectAttributes) {

        try {
            EmpAbilityInfoPK pk = new EmpAbilityInfoPK(
            		dto.getEmpId(),
            		dto.getAbilityNm()
                );

            if (profileService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/profile/addEmpAbilityInfo";
            }
        	
            profileService.saveEmpAbilityInfo(dto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/profile/addEmpAbilityInfo";
    }

    // 직무 능력 수정
    @GetMapping("/profile/editEmpAbilityInfo")
    public String editEmpAbilityInfoForm(
            @RequestParam("empId") String empId,
            //@RequestParam("empNm") String empNm,
            @RequestParam("abilityType") String abilityType,
            @RequestParam("abilityNm") String abilityNm,
            @RequestParam("sysCretDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate sysCretDate,
            Model model) {

    	
    	EmpAbilityInfoPK pk = new EmpAbilityInfoPK(empId, abilityNm);
    	EmpAbilityInfo empAbilityInfo = profileService.findByPk(pk);

    	model.addAttribute("empId", empId);
        model.addAttribute("abilityType", abilityType);
        model.addAttribute("abilityNm", abilityNm);
        model.addAttribute("sysCretDate", sysCretDate);
        
        return "profile/editEmpAbilityInfo";
    }
    
    @PostMapping("/profile/editEmpAbilityInfo")
    public String editEmpAbilityInfoPage(@ModelAttribute EmpAbilityInfoDTO dto, RedirectAttributes redirectAttributes) {
        try {
        	EmpAbilityInfoPK pk = new EmpAbilityInfoPK(
        			dto.getEmpId(),
        			dto.getAbilityNm()
            );

        	EmpAbilityInfo existingEmpAbilityInfo = profileService.findByPk(pk);

            profileService.updateEmpAbilityInfoFromDto(dto, existingEmpAbilityInfo);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/profile/editEmpAbilityInfo?empId=" + URLEncoder.encode(dto.getEmpId(), StandardCharsets.UTF_8)
        	+ "&abilityType=" + URLEncoder.encode(dto.getAbilityType(), StandardCharsets.UTF_8)
        	+ "&abilityNm=" + URLEncoder.encode(dto.getAbilityNm(), StandardCharsets.UTF_8)
        	+ "&sysCretDate=" + dto.getSysCretDate()
        	;
    }
    
    // 직무 능력 삭제
    @GetMapping("/profile/deleteEmpAbilityInfo")
    public String deleteEmpAbilityInfoPage(@RequestParam("empId") List<String> empIds, RedirectAttributes redirectAttributes) {
        try {
            for (String empId : empIds) {
                profileService.deleteEmpAbilityInfo(empId);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/profile/empAbilityInfo";
    }
}



