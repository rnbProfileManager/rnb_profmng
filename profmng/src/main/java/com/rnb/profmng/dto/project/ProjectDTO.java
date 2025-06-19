package com.rnb.profmng.dto.project;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rnb.profmng.entity.project.Project;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
    
	public ProjectDTO(long pjtSeq, String pjtNm, LocalDate efctStartDate, LocalDate efctEndDate,
			String pmId, String client, String contractor, BigDecimal manMonth,
			BigDecimal totAmt, String pjtTypeCd) {
		this.pjtSeq = pjtSeq;
		this.pjtNm = pjtNm;
		this.efctStartDate = efctStartDate;
		this.efctEndDate = efctEndDate;
		this.pmId = pmId;
		this.client = client;
		this.contractor = contractor;
		this.manMonth = manMonth;
		this.totAmt = totAmt;
		this.pjtTypeCd = pjtTypeCd;
	}
    
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
