package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.EmpAbility;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpAbilityDTO{
    
	private String empCd;
	//private String empNm;
    private LocalDate startDate;
    //private LocalDate endDate;
    private LocalDate updateDate;
    private String abilityType;
    private String abilityNm;
    
	public EmpAbilityDTO(String empCd, String empNm, LocalDate startDate, LocalDate endDate,
			LocalDate updateDate, String abilityType, String abilityNm) {
		this.empCd = empCd;
		//this.empNm = empNm;
		this.startDate = startDate;
		//this.endDate = endDate;
		this.updateDate = updateDate;
		this.abilityType = abilityType;
		this.abilityNm = abilityNm;
	}
    
    public EmpAbilityDTO(EmpAbility empAbility) {
    	this.empCd = empAbility.getEmpAbilityPk().getEmpCd();
    	//this.empNm = empAbility.getEmpAbilityPk().getEmpNm();
    	this.startDate = empAbility.getEmpAbilityPk().getStartDate();
    	//this.endDate = empAbility.getEndDate();
    	this.updateDate = empAbility.getUpdateDate();
    	this.abilityType = empAbility.getEmpAbilityPk().getAbilityType();
    	this.abilityNm = empAbility.getEmpAbilityPk().getAbilityNm();
    }
}
