package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.EmpBas;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpBasDTO{
    
	private String empId;
    private LocalDate efctStartDate;

    private String empNm;
    private LocalDate efctEndDate;
    private String jobGradeCd;
    private String jobTitleCd;
    private String homeAddr;
    private String callNumber;
    private String orgCd;
    private String empTypeCd;
    
	public EmpBasDTO(String empId, LocalDate efctStartDate, String empNm, LocalDate efctEndDate, String jobGradeCd,
			String jobTitleCd, String homeAddr, String callNumber, String orgCd, String empTypeCd) {
		this.empId = empId;
		this.efctStartDate = efctStartDate;
		this.empNm = empNm;
		this.efctEndDate = efctEndDate;
		this.jobGradeCd = jobGradeCd;
		this.jobTitleCd = jobTitleCd;
		this.homeAddr = homeAddr;
		this.callNumber = callNumber;
		this.orgCd = orgCd;
		this.empTypeCd = empTypeCd;
	}
    
    public EmpBasDTO(EmpBas empBas) {
    	this.empId = empBas.getEmpBasPk().getEmpId();
    	this.efctStartDate = empBas.getEmpBasPk().getEfctStartDate();
    	this.empNm = empBas.getEmpNm();
    	this.efctEndDate = empBas.getEfctEndDate();
    	this.jobGradeCd = empBas.getJobGradeCd();
    	this.jobTitleCd = empBas.getJobTitleCd();
    	this.homeAddr = empBas.getHomeAddr();
    	this.callNumber = empBas.getCallNumber();
    	this.orgCd = empBas.getOrgCd();
    	this.empTypeCd = empBas.getEmpTypeCd();
    }
}
