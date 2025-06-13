package com.rnb.profmng.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.dto.ProfiledbDto;
import com.rnb.profmng.dto.profile.EmpAbilityDTO;
import com.rnb.profmng.dto.profile.EmpNoDTO;
import com.rnb.profmng.dto.profile.ProfileDTO;
import com.rnb.profmng.dto.profile.ProjectEmpInfoDTO;
import com.rnb.profmng.dto.project.ProjectDTO;
import com.rnb.profmng.entity.profile.EmpAbility;
import com.rnb.profmng.entity.profile.EmpAbilityPK;
import com.rnb.profmng.entity.profile.EmpNo;
import com.rnb.profmng.entity.profile.EmpNoPK;
import com.rnb.profmng.entity.profile.ProjectEmpInfo;
import com.rnb.profmng.entity.profile.ProjectEmpInfoPK;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.entity.project.ProjectPK;
import com.rnb.profmng.repository.ProfileRepository;
import com.rnb.profmng.repository.profile.EmpAbilityRepo;
import com.rnb.profmng.repository.profile.EmpNoRepo;
import com.rnb.profmng.repository.profile.ProjectEmpInfoRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class ProfileService {
    @Autowired
    private EmpNoRepo empNoRepo;

    @Autowired
    private EmpAbilityRepo empAbilityRepo;

    @Autowired
    private ProjectEmpInfoRepo projectEmpInfoRepo;
    
    private final ProfileRepository profileRepository;
	 
	// 전체 조회
	public List<ProfiledbDto> getAllProfiles() {
		List<ProfiledbDto> dto = profileRepository.getAllProfiles();

		return dto;
	}
	 
	// 조회조건 조회   
	 public List<ProfiledbDto> searchProfiles(String empNm, String startDateStr, String endDateStr) {
	        if ((empNm == null || empNm.isEmpty()) 
	            && (startDateStr == null || startDateStr.isEmpty()) 
	            && (endDateStr == null || endDateStr.isEmpty())) {
	            return getAllProfiles();
	        }

	        LocalDateTime parsedStartDate = (startDateStr != null && !startDateStr.isEmpty())
	                ? LocalDate.parse(startDateStr).atStartOfDay()
	                : null;

	        LocalDateTime parsedEndDate = (endDateStr != null && !endDateStr.isEmpty())
	                ? LocalDate.parse(endDateStr).atTime(23, 59, 59)
	                : null;

	        return profileRepository.searchProfiles(empNm, parsedStartDate, parsedEndDate);
	    }
    

    public EmpNo insertEmpNo(ProfileDTO profileDto) {
    	
        EmpNoPK id = new EmpNoPK(profileDto.getEmpCd(), profileDto.getEmpNm(), profileDto.getStartDate());

        if (empNoRepo.existsById(id)) {
            throw new DuplicateKeyException("Duplicate");
        }
    	
        EmpNoPK empNoId = new EmpNoPK();
        empNoId.setEmpCd(profileDto.getEmpCd());
        empNoId.setEmpNm(profileDto.getEmpNm());
        empNoId.setStartDate(profileDto.getStartDate());
        
        EmpNo empNo = new EmpNo();
        empNo.setEmpNoPk(empNoId);
        
        empNo.setEndDate(profileDto.getEndDate());
        empNo.setUpdateDate(profileDto.getUpdateDate());
        empNo.setJobGrade(profileDto.getJobGrade());
        empNo.setJobTitle(profileDto.getJobTitle());
        empNo.setAddress(profileDto.getAddress());
        empNo.setCallNumber(profileDto.getCallNumber());
        empNo.setOrgNm(profileDto.getOrgNm());
        empNo.setEmpType(profileDto.getEmpType());

        return empNoRepo.save(empNo);
    }
    
    public EmpAbility insertEmpAbility(ProfileDTO profileDto) {
    	
        EmpAbilityPK id = new EmpAbilityPK(profileDto.getEmpCd(), profileDto.getEmpNm(), profileDto.getAbilityType(), profileDto.getAbilityNm(), profileDto.getStartDate());

        if (empAbilityRepo.existsById(id)) {
            throw new DuplicateKeyException("Duplicate");
        }
    	
        EmpAbilityPK empAbilityPk = new EmpAbilityPK();
        empAbilityPk.setEmpCd(profileDto.getEmpCd());
        empAbilityPk.setEmpNm(profileDto.getEmpNm());
        empAbilityPk.setAbilityType(profileDto.getAbilityType());
        empAbilityPk.setAbilityNm(profileDto.getAbilityNm());
        empAbilityPk.setStartDate(profileDto.getStartDate());
        
        EmpAbility empAbility = new EmpAbility();
        empAbility.setEmpAbilityPk(empAbilityPk);
        
        empAbility.setEndDate(profileDto.getEndDate());
        empAbility.setUpdateDate(profileDto.getUpdateDate());

        return empAbilityRepo.save(empAbility);
    }
    
    public ProjectEmpInfo insertProjectEmpNo(ProfileDTO profileDto) {
    	
        ProjectEmpInfoPK id = new ProjectEmpInfoPK(profileDto.getProjectCd(), profileDto.getEmpCd(), profileDto.getProjectNm(), profileDto.getStartDate());

        if (projectEmpInfoRepo.existsById(id)) {
            throw new DuplicateKeyException("Duplicate");
        }
    	
        ProjectEmpInfoPK projectEmpInfoPk = new ProjectEmpInfoPK();
        projectEmpInfoPk.setProjectCd(profileDto.getProjectCd());
        projectEmpInfoPk.setEmpCd(profileDto.getEmpCd());
        projectEmpInfoPk.setProjectNm(profileDto.getProjectNm());
        projectEmpInfoPk.setStartDate(profileDto.getStartDate());
        
        ProjectEmpInfo projectEmpInfo = new ProjectEmpInfo();
        projectEmpInfo.setProjectEmpInfoPk(projectEmpInfoPk);
        
        projectEmpInfo.setEndDate(profileDto.getEndDate());
        projectEmpInfo.setUpdateDate(profileDto.getUpdateDate());
        projectEmpInfo.setUserRole(profileDto.getUserRole());

        return projectEmpInfoRepo.save(projectEmpInfo);
    }
    
    
    /*
     * 20250613
     * 직원 정보
     */
    
    // 모든 직원 정보 조회
    public List<EmpNoDTO> allEmpInfos() {
        return empNoRepo.findAll().stream()
                .map(EmpNoDTO::new)
                .collect(Collectors.toList());
    }
    
    // 신규 직원 정보 추가
    public void saveEmpNo(EmpNoDTO dto) {
        EmpNo entity = new EmpNo();

        EmpNoPK pk = new EmpNoPK();
        pk.setEmpCd(dto.getEmpCd());
        pk.setEmpNm(dto.getEmpNm());
        pk.setStartDate(dto.getStartDate());

        entity.setEmpNoPk(pk);

        entity.setEndDate(dto.getEndDate());
        entity.setJobGrade(dto.getJobGrade());
        entity.setJobTitle(dto.getJobTitle());
        entity.setAddress(dto.getAddress());
        entity.setCallNumber(dto.getCallNumber());
        entity.setOrgNm(dto.getOrgNm());
        entity.setEmpType(dto.getEmpType());

        empNoRepo.save(entity);
    }
    
    // 직원 정보 삭제
    public int deleteEmpNo(String empCd) {
        return empNoRepo.deleteByEmpCd(empCd);
    }
    
    // 직원 정보 수정 - PK 체크    
    public EmpNo findByPk(EmpNoPK pk) {
        return empNoRepo.findById(pk)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    
    // 직원 정보 추가 - PK 체크
    public boolean existsByPk(EmpNoPK pk) {
        return empNoRepo.existsById(pk);
    }
    
    // 직원 정보 수정
    @Transactional
    public void updateEmpNoFromDto(EmpNoDTO dto, EmpNo entity) {
        try {
            new NullAwareBeanUtilsBean().copyProperties(entity, dto);
            entity.setUpdateDate(LocalDate.now());
        } catch (Exception e) {
            throw new RuntimeException("프로퍼티 복사 실패", e);
        }
    }
    
    
    
    
    /*
     * 직무 능력
     */

    // 모든 직무 능력 조회
    public List<EmpAbilityDTO> allEmpAbilitys() {
        return empAbilityRepo.findAll().stream()
                .map(EmpAbilityDTO::new)
                .collect(Collectors.toList());
    }
    
    // 신규 직무 능력 추가
    public void saveEmpAbility(EmpAbilityDTO dto) {
        EmpAbility entity = new EmpAbility();

        EmpAbilityPK pk = new EmpAbilityPK();
        pk.setEmpCd(dto.getEmpCd());
        pk.setEmpNm(dto.getEmpNm());
        pk.setAbilityType(dto.getAbilityType());
        pk.setAbilityNm(dto.getAbilityNm());
        pk.setStartDate(dto.getStartDate());

        entity.setEmpAbilityPk(pk);

        entity.setEndDate(dto.getEndDate());

        empAbilityRepo.save(entity);
    }
    
    // 직무 능력 삭제
    public int deleteEmpAbility(String empCd) {
        return empAbilityRepo.deleteByEmpCd(empCd);
    }
    
    // 직무 능력 수정 - PK 체크    
    public EmpAbility findByPk(EmpAbilityPK pk) {
        return empAbilityRepo.findById(pk)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    
    // 직무 능력 추가 - PK 체크
    public boolean existsByPk(EmpAbilityPK pk) {
        return empAbilityRepo.existsById(pk);
    }
    
    // 직무 능력 수정
    @Transactional
    public void updateEmpAbilityFromDto(EmpAbilityDTO dto, EmpAbility entity) {
        try {
            new NullAwareBeanUtilsBean().copyProperties(entity, dto);
            entity.setUpdateDate(LocalDate.now());
        } catch (Exception e) {
            throw new RuntimeException("프로퍼티 복사 실패", e);
        }
    }
    
    
    
    
    /*
     * 투입 인력
     */
    
    // 모든 투입 인력 조회
    public List<ProjectEmpInfoDTO> allProjectInfos() {
        return projectEmpInfoRepo.findAll().stream()
                .map(ProjectEmpInfoDTO::new)
                .collect(Collectors.toList());
    }
    
    // 신규 투입 인력 추가
    public void saveProjectEmpInfo(ProjectEmpInfoDTO dto) {
    	ProjectEmpInfo entity = new ProjectEmpInfo();

    	ProjectEmpInfoPK pk = new ProjectEmpInfoPK();
        pk.setProjectCd(dto.getProjectCd());
        pk.setEmpCd(dto.getEmpCd());
        pk.setProjectNm(dto.getProjectNm());
        pk.setStartDate(dto.getStartDate());

        entity.setProjectEmpInfoPk(pk);

        entity.setEndDate(dto.getEndDate());
        entity.setUserRole(dto.getUserRole());

        projectEmpInfoRepo.save(entity);
    }
    
    // 투입 인력 삭제
    public int deleteProjectEmpInfo(String empCd) {
        return projectEmpInfoRepo.deleteByEmpCd(empCd);
    }
    
    // 투입 인력 수정 - PK 체크    
    public ProjectEmpInfo findByPk(ProjectEmpInfoPK pk) {
        return projectEmpInfoRepo.findById(pk)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    
    // 투입 인력 추가 - PK 체크
    public boolean existsByPk(ProjectEmpInfoPK pk) {
        return projectEmpInfoRepo.existsById(pk);
    }
    
    // 투입 인력 수정
    @Transactional
    public void updateProjectEmpInfoFromDto(ProjectEmpInfoDTO dto, ProjectEmpInfo entity) {
        try {
            new NullAwareBeanUtilsBean().copyProperties(entity, dto);
            entity.setUpdateDate(LocalDate.now());
        } catch (Exception e) {
            throw new RuntimeException("프로퍼티 복사 실패", e);
        }
    }
    

}






