package com.rnb.profmng.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.rnb.profmng.repository.ProfileRepository;
import com.rnb.profmng.repository.profile.EmpAbilityInfoRepo;
import com.rnb.profmng.repository.profile.EmpBasRepo;
import com.rnb.profmng.repository.profile.PjtHmnResrcInfoRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class ProfileService {
    @Autowired
    private EmpBasRepo empBasRepo;

    @Autowired
    private EmpAbilityInfoRepo empAbilityInfoRepo;

    @Autowired
    private PjtHmnResrcInfoRepo pjtHmnResrcInfoRepo;
    
    private final ProfileRepository profileRepository;
	 
	// 전체 조회
	public List<ProfiledbDto> getAllProfiles() {
		List<ProfiledbDto> dto = profileRepository.getAllProfiles();
		return dto;
	}
	 
	// 조회조건 조회   
	 public List<ProfiledbDto> searchProfiles(String empId) {
	        if (empId == null || empId.isEmpty()) { 
	            return getAllProfiles();
	        }

	        return profileRepository.searchProfiles(empId);
	    }
    

    public EmpBas insertEmpBas(EmpBasDTO dto) {
    	
        EmpBasPK id = new EmpBasPK(dto.getEmpId(), dto.getEfctStartDate());

        if (empBasRepo.existsById(id)) {
            throw new DuplicateKeyException("Duplicate");
        }
    	
        EmpBasPK pk = new EmpBasPK();
        pk.setEmpId(dto.getEmpId());
        pk.setEfctStartDate(dto.getEfctStartDate());
        
        EmpBas empBas = new EmpBas();
        
        empBas.setEmpBasPk(pk);
        empBas.setEmpNm(dto.getEmpNm());
        empBas.setEfctEndDate(dto.getEfctEndDate());
        empBas.setJobGradeCd(dto.getJobGradeCd());
        empBas.setJobTitleCd(dto.getJobTitleCd());
        empBas.setHomeAddr(dto.getHomeAddr());
        empBas.setCallNumber(dto.getCallNumber());
        empBas.setOrgCd(dto.getOrgCd());
        empBas.setEmpTypeCd(dto.getEmpTypeCd());

        return empBasRepo.save(empBas);
    }
    
    public EmpAbilityInfo insertEmpAbilityInfo(EmpAbilityInfoDTO dto) {
    	
        EmpAbilityInfoPK id = new EmpAbilityInfoPK(dto.getEmpId(), 
        		dto.getAbilityNm());

        if (empAbilityInfoRepo.existsById(id)) {
            throw new DuplicateKeyException("Duplicate");
        }
    	
        EmpAbilityInfoPK pk = new EmpAbilityInfoPK();
        pk.setEmpId(dto.getEmpId());
        pk.setAbilityNm(dto.getAbilityNm());
        
        EmpAbilityInfo empAbilityInfo = new EmpAbilityInfo();
        
        empAbilityInfo.setEmpAbilityInfoPk(pk);
        empAbilityInfo.setAbilityType(dto.getAbilityType());
        empAbilityInfo.setSysUpdtrId(dto.getSysUpdtrId());
        empAbilityInfo.setSysSvcId(dto.getSysSvcId());
        empAbilityInfo.setSysCretDate(dto.getSysCretDate());
        empAbilityInfo.setSysUpdtDate(dto.getSysUpdtDate());

        return empAbilityInfoRepo.save(empAbilityInfo);
    }
    
    public PjtHmnResrcInfo insertPjtHmnResrcInfo(PjtHmnResrcInfoDTO dto) {
    	
        PjtHmnResrcInfoPK id = new PjtHmnResrcInfoPK(dto.getPjtSeq(),
        		dto.getEmpId(),
        		dto.getEfctStartDate());

        if (pjtHmnResrcInfoRepo.existsById(id)) {
            throw new DuplicateKeyException("Duplicate");
        }
    	
        PjtHmnResrcInfoPK pk = new PjtHmnResrcInfoPK();
        pk.setPjtSeq(dto.getPjtSeq());
        pk.setEmpId(dto.getEmpId());
        pk.setEfctStartDate(dto.getEfctStartDate());
        
        PjtHmnResrcInfo pjtHmnResrcInfo = new PjtHmnResrcInfo();

        pjtHmnResrcInfo.setPjtHmnResrcInfoPk(pk);
        pjtHmnResrcInfo.setEfctEndDate(dto.getEfctEndDate());
        pjtHmnResrcInfo.setSysUpdtrId(dto.getSysUpdtrId());
        pjtHmnResrcInfo.setSysSvcId(dto.getSysSvcId());
        pjtHmnResrcInfo.setSysCretDate(dto.getSysCretDate());
        pjtHmnResrcInfo.setSysUpdtDate(dto.getSysUpdtDate());
        pjtHmnResrcInfo.setUserRoleCd(dto.getUserRoleCd());

        return pjtHmnResrcInfoRepo.save(pjtHmnResrcInfo);
    }
    
    
    /*
     * 20250613
     * 직원 정보
     */
    
    // 모든 직원 정보 조회
    public List<EmpBasDTO> allEmpBas() {
        return empBasRepo.findAll().stream()
                .map(EmpBasDTO::new)
                .collect(Collectors.toList());
    }
    
    // 신규 직원 정보 추가
    public void saveEmpBas(EmpBasDTO dto) {
        EmpBas entity = new EmpBas();

        EmpBasPK pk = new EmpBasPK();
        pk.setEmpId(dto.getEmpId());
        pk.setEfctStartDate(dto.getEfctStartDate());

        entity.setEmpBasPk(pk);

        entity.setEmpNm(dto.getEmpNm());
        entity.setEfctEndDate(dto.getEfctEndDate());
        entity.setJobGradeCd(dto.getJobGradeCd());
        entity.setJobTitleCd(dto.getJobTitleCd());
        entity.setHomeAddr(dto.getHomeAddr());
        entity.setCallNumber(dto.getCallNumber());
        entity.setOrgCd(dto.getOrgCd());
        entity.setEmpTypeCd(dto.getEmpTypeCd());

        empBasRepo.save(entity);
    }
    
    // 직원 정보 삭제
    public int deleteEmpBas(String empId) {
        return empBasRepo.deleteByEmpId(empId);
    }
    
    // 직원 정보 수정 - PK 체크    
    public EmpBas findByPk(EmpBasPK pk) {
        return empBasRepo.findById(pk)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    
    // 직원 정보 추가 - PK 체크
    public boolean existsByPk(EmpBasPK pk) {
        return empBasRepo.existsById(pk);
    }
    
    // 직원 정보 수정
    @Transactional
    public void updateEmpBasFromDto(EmpBasDTO dto, EmpBas entity) {
        try {
            new NullAwareBeanUtilsBean().copyProperties(entity, dto);
        } catch (Exception e) {
            throw new RuntimeException("프로퍼티 복사 실패", e);
        }
    }
    
    
    
    
    /*
     * 직무 능력
     */

    // 모든 직무 능력 조회
    public List<EmpAbilityInfoDTO> allEmpAbilityInfo() {
        return empAbilityInfoRepo.findAll().stream()
                .map(EmpAbilityInfoDTO::new)
                .collect(Collectors.toList());
    }
    
    // 신규 직무 능력 추가
    public void saveEmpAbilityInfo(EmpAbilityInfoDTO dto) {
        EmpAbilityInfo entity = new EmpAbilityInfo();

        EmpAbilityInfoPK pk = new EmpAbilityInfoPK();
        pk.setEmpId(dto.getEmpId());
        pk.setAbilityNm(dto.getAbilityNm());

        entity.setEmpAbilityInfoPk(pk);

        entity.setAbilityType(dto.getAbilityType());
        entity.setSysUpdtrId(dto.getSysUpdtrId());
        entity.setSysSvcId(dto.getSysSvcId());
        entity.setSysCretDate(dto.getSysCretDate());
        entity.setSysUpdtDate(dto.getSysUpdtDate());

        empAbilityInfoRepo.save(entity);
    }
    
    // 직무 능력 삭제
    public int deleteEmpAbilityInfo(String empNm) {
        return empAbilityInfoRepo.deleteByEmpNm(empNm);
    }
    
    // 직무 능력 수정 - PK 체크    
    public EmpAbilityInfo findByPk(EmpAbilityInfoPK pk) {
        return empAbilityInfoRepo.findById(pk)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    
    // 직무 능력 추가 - PK 체크
    public boolean existsByPk(EmpAbilityInfoPK pk) {
        return empAbilityInfoRepo.existsById(pk);
    }
    
    // 직무 능력 수정
    @Transactional
    public void updateEmpAbilityInfoFromDto(EmpAbilityInfoDTO dto, EmpAbilityInfo entity) {
        try {
            new NullAwareBeanUtilsBean().copyProperties(entity, dto);
            entity.setSysUpdtDate(LocalDate.now());
        } catch (Exception e) {
            throw new RuntimeException("프로퍼티 복사 실패", e);
        }
    }
    
    
    
    
    /*
     * 투입 인력
     */
    
    // 모든 투입 인력 조회
    public List<PjtHmnResrcInfoDTO> allPjtHmnResrcInfo() {
        return pjtHmnResrcInfoRepo.findAll().stream()
                .map(PjtHmnResrcInfoDTO::new)
                .collect(Collectors.toList());
    }
    
    // 신규 투입 인력 추가
    public void savePjtHmnResrcInfo(PjtHmnResrcInfoDTO dto) {
    	PjtHmnResrcInfo entity = new PjtHmnResrcInfo();

    	PjtHmnResrcInfoPK pk = new PjtHmnResrcInfoPK();
        pk.setPjtSeq(dto.getPjtSeq());
        pk.setEmpId(dto.getEmpId());
        pk.setEfctStartDate(dto.getEfctStartDate());

        entity.setPjtHmnResrcInfoPk(pk);

        entity.setEfctEndDate(dto.getEfctEndDate());
        entity.setSysUpdtrId(dto.getSysUpdtrId());
        entity.setSysSvcId(dto.getSysSvcId());
        entity.setSysCretDate(dto.getSysCretDate());
        entity.setSysUpdtDate(dto.getSysUpdtDate());
        entity.setUserRoleCd(dto.getUserRoleCd());

        pjtHmnResrcInfoRepo.save(entity);
    }
    
    // 투입 인력 삭제
    public int deletePjtHmnResrcInfo(String pjtSeq) {
        return pjtHmnResrcInfoRepo.deleteByPjtHmnResrcInfoPK_PjtSeq(pjtSeq);
    }
    
    // 투입 인력 수정 - PK 체크    
    public PjtHmnResrcInfo findByPk(PjtHmnResrcInfoPK pk) {
        return pjtHmnResrcInfoRepo.findById(pk)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    
    // 투입 인력 추가 - PK 체크
    public boolean existsByPk(PjtHmnResrcInfoPK pk) {
        return pjtHmnResrcInfoRepo.existsById(pk);
    }
    
    // 투입 인력 수정
    @Transactional
    public void updatePjtHmnResrcInfoFromDto(PjtHmnResrcInfoDTO dto, PjtHmnResrcInfo entity) {
        try {
            new NullAwareBeanUtilsBean().copyProperties(entity, dto);
            entity.setSysUpdtDate(LocalDate.now());
        } catch (Exception e) {
            throw new RuntimeException("프로퍼티 복사 실패", e);
        }
    }
    

}






