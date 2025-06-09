package com.rnb.profmng.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.rnb.profmng.entity.project.Project;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDTO {
    
	// PROJECT_TABLE
	private String projectCd;
    private String projectNm;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String pmId;
    private String client;
    private String contractor;
    private BigDecimal manMonth;
    private BigDecimal totAmt;
    private String projectType;
    
    
    
    // --- 로그인 요청을 위한 생성자 ---
    public ProjectDTO(String projectCd, String projectNm) {
        this.projectCd = projectCd;
        this.projectNm = projectNm;
    }
    
    // User 엔티티를 DTO로 변환하기 위한 생성자( 응답 )
    public ProjectDTO(Project project) {
    	this.projectCd = project.getProjectCd();
    	this.projectNm = project.getProjectNm();
    	this.startDate = project.getStartDate();
    	this.endDate = project.getEndDate();
    	this.pmId = project.getPmId();
    	this.client = project.getClient();
    	this.contractor = project.getContractor();
    	this.manMonth = project.getManMonth();
    	this.totAmt = project.getTotAmt();
    	this.projectType = project.getProjectType();
    }
}
