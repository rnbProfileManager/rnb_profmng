package com.rnb.profmng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.rnb.profmng.dto.ProfileDTO;
import com.rnb.profmng.entity.profile.EmpAbility;
import com.rnb.profmng.entity.profile.EmpAbilityPK;
import com.rnb.profmng.entity.profile.EmpNo;
import com.rnb.profmng.entity.profile.EmpNoPK;
import com.rnb.profmng.entity.profile.ProjectEmpInfo;
import com.rnb.profmng.entity.profile.ProjectEmpInfoPK;
import com.rnb.profmng.repository.EmpAbilityRepo;
import com.rnb.profmng.repository.EmpNoRepo;
import com.rnb.profmng.repository.ProjectEmpInfoRepo;

@Service
public class ProfileService {
    @Autowired
    private EmpNoRepo empNoRepo;

    @Autowired
    private EmpAbilityRepo empAbilityRepo;

    @Autowired
    private ProjectEmpInfoRepo projectEmpInfoRepo;

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
    
}






