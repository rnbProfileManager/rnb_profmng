package com.rnb.profmng.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rnb.profmng.entity.project.Project;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDTO implements ProjectView {
    
	// PROJECT_TABLE
	private String projectCd;
    private String projectNm;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate updateDate;
    private String pmId;
    private String client;
    private String contractor;
    private BigDecimal manMonth;
    private BigDecimal totAmt;
    private String projectType;
    
	public ProjectDTO(String projectCd, String projectNm, LocalDate startDate, LocalDate endDate,
			LocalDate updateDate, String pmId, String client, String contractor, BigDecimal manMonth,
			BigDecimal totAmt, String projectType) {
		this.projectCd = projectCd;
		this.projectNm = projectNm;
		this.startDate = startDate;
		this.endDate = endDate;
		this.updateDate = updateDate;
		this.pmId = pmId;
		this.client = client;
		this.contractor = contractor;
		this.manMonth = manMonth;
		this.totAmt = totAmt;
		this.projectType = projectType;
	}
    
    // User 엔티티를 DTO로 변환하기 위한 생성자( 응답 )
    public ProjectDTO(Project project) {
    	this.projectCd = project.getProjectPk().getProjectCd();
    	this.projectNm = project.getProjectPk().getProjectNm();
    	this.startDate = project.getProjectPk().getStartDate();
    	this.endDate = project.getEndDate();
    	this.updateDate = project.getUpdateDate();
    	this.pmId = project.getPmId();
    	this.client = project.getClient();
    	this.contractor = project.getContractor();
    	this.manMonth = project.getManMonth();
    	this.totAmt = project.getTotAmt();
    	this.projectType = project.getProjectType();
    }
}
