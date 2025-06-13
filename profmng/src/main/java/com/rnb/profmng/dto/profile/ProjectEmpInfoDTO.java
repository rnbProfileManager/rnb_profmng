package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.ProjectEmpInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectEmpInfoDTO{
    
	private String projectCd;
    private String empCd;
    private String projectNm;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate updateDate;
    private String userRole;
    
	public ProjectEmpInfoDTO(String projectCd, String empCd, String projectNm, LocalDate startDate,
			LocalDate endDate, LocalDate updateDate, String userRole) {
		this.projectCd = projectCd;
		this.empCd = empCd;
		this.projectNm = projectNm;
		this.startDate = startDate;
		this.endDate = endDate;
		this.updateDate = updateDate;
		this.userRole = userRole;
	}
    
    public ProjectEmpInfoDTO(ProjectEmpInfo projectEmpInfo) {
    	this.projectCd = projectEmpInfo.getProjectEmpInfoPk().getProjectCd();
    	this.empCd = projectEmpInfo.getProjectEmpInfoPk().getEmpCd();
    	this.projectNm = projectEmpInfo.getProjectEmpInfoPk().getProjectNm();
    	this.startDate = projectEmpInfo.getProjectEmpInfoPk().getStartDate();
    	this.endDate = projectEmpInfo.getEndDate();
    	this.updateDate = projectEmpInfo.getUpdateDate();
    	this.userRole = projectEmpInfo.getUserRole();
    }
}
