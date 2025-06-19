package com.rnb.profmng.dto.project;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rnb.profmng.dto.profile.EmpAbilityInfoDTO;
import com.rnb.profmng.entity.project.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    
	private long pjtSeq;
    private String pjtNm;
    private LocalDate efctStartDate;
    private LocalDate efctEndDate;
    private String pmId;
    private String client;
    private String contractor;
    private BigDecimal manMonth;
    private BigDecimal totAmt;
    private String pjtTypeCd;
    
    public ProjectDTO(Project project) {
    	this.pjtSeq = project.getProjectPk().getPjtSeq();
    	this.pjtNm = project.getProjectPk().getPjtNm();
    	this.efctStartDate = project.getProjectPk().getEfctStartDate();
    	this.efctEndDate = project.getEfctEndDate();
    	this.pmId = project.getPmId();
    	this.client = project.getClient();
    	this.contractor = project.getContractor();
    	this.manMonth = project.getManMonth();
    	this.totAmt = project.getTotAmt();
    	this.pjtTypeCd = project.getPjtTypeCd();
    }
}
