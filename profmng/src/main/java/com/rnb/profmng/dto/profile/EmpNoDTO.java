package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.EmpNo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpNoDTO implements EmpNoView{
    
	private String empCd;
    private String empNm;
    private LocalDate startDate;
    private LocalDate endDate;
    //private LocalDate updateDate;
    private String jobGrade;
    private String jobTitle;
    private String address;
    private String callNumber;
    private String orgNm;
    private String empType;
    
    
	public EmpNoDTO(String empCd, String empNm, LocalDate startDate, LocalDate endDate,
			LocalDate updateDate, String jobGrade, String jobTitle, String address, String callNumber,
			String orgNm, String empType) {
		this.empCd = empCd;
		this.empNm = empNm;
		this.startDate = startDate;
		this.endDate = endDate;
		//this.updateDate = updateDate;
		this.jobGrade = jobGrade;
		this.jobTitle = jobTitle;
		this.address = address;
		this.callNumber = callNumber;
		this.orgNm = orgNm;
		this.empType = empType;
	}
    
    public EmpNoDTO(EmpNo empNo) {
    	this.empCd = empNo.getEmpNoPk().getEmpCd();
    	this.empNm = empNo.getEmpNoPk().getEmpNm();
    	this.startDate = empNo.getEmpNoPk().getStartDate();
    	this.endDate = empNo.getEndDate();
    	//this.updateDate = empNo.getUpdateDate();
    	this.jobGrade = empNo.getJobGrade();
    	this.jobTitle = empNo.getJobTitle();
    	this.address = empNo.getAddress();
    	this.callNumber = empNo.getCallNumber();
    	this.orgNm = empNo.getOrgNm();
    	this.empType = empNo.getEmpType();
    }
}
