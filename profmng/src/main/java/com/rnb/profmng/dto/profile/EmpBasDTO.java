package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.EmpBas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
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
