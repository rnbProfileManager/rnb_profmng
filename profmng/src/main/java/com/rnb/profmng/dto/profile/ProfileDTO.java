package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDTO {
	
	// EMP_NO, PROJECT_EMP_INFO, EMP_ABILITY 중복
	private String empCd;			// 사원코드
	private String empNm;			// 사원명
    private LocalDate startDate;	// 시작일자
    private LocalDate endDate;		// 종료일자
    private LocalDate updateDate;	// 종료일자
    
	// EMP_NO
    private String jobGrade;		// 직급
    private String jobTitle;		// 직위
    private String address;			// 주소
    private String callNumber;		// 전화번호
    private String orgNm;			// 부서
    private String empType;			// 고용형태
	
	// PROJECT_EMP_INFO
    private String projectCd;		// 프로젝트코드
    private String projectNm;		// 프로젝트명
    private String userRole;		// 역할
    
    // EMP_ABILITY
    private String abilityType;		// 직무능력형태
    private String abilityNm;		// 직무능력명
    
    // --- 로그인 요청을 위한 생성자 ---
    public ProfileDTO(String projectCd, String projectNm) {
        this.projectCd = projectCd;
        this.projectNm = projectNm;
    }
    
    // User 엔티티를 DTO로 변환하기 위한 생성자( 응답 )
    public ProfileDTO(Profile profile) {
    	this.empCd = profile.getEmpNo().getEmpNoPk().getEmpCd();
    	this.empNm = profile.getEmpNo().getEmpNoPk().getEmpNm();
    	this.startDate = profile.getEmpNo().getEmpNoPk().getStartDate();
    	this.endDate = profile.getEmpNo().getEndDate();
    	//this.updateDate = profile.getEmpNo().getUpdateDate();
    	this.jobGrade = profile.getEmpNo().getJobGrade();
    	this.jobTitle = profile.getEmpNo().getJobTitle();
    	this.address = profile.getEmpNo().getAddress();
    	this.callNumber = profile.getEmpNo().getCallNumber();
    	this.orgNm = profile.getEmpNo().getOrgNm();
    	this.empType = profile.getEmpNo().getEmpType();
    	this.projectCd = profile.getProjectEmpInfo().getProjectEmpInfoPk().getProjectCd();
    	this.projectNm = profile.getProjectEmpInfo().getProjectEmpInfoPk().getProjectNm();
    	this.userRole = profile.getProjectEmpInfo().getUserRole();
    	this.abilityType = profile.getEmpAbility().getEmpAbilityPk().getAbilityType();
    	this.abilityNm = profile.getEmpAbility().getEmpAbilityPk().getAbilityNm();
    }
}
