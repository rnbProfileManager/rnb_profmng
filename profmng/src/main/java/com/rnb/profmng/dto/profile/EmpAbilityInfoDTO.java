package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.EmpAbilityInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpAbilityInfoDTO{
    
	private String empId;
    private String abilityNm;
    
    private String abilityType;
    private String sysUpdtrId;
    private String sysSvcId;
    private LocalDate sysCretDate;
    private LocalDate sysUpdtDate;
    
	public EmpAbilityInfoDTO(String empId, String abilityNm, String abilityType, String sysUpdtrId, String sysSvcId,
			LocalDate sysCretDate, LocalDate sysUpdtDate) {
		this.empId = empId;
		this.abilityNm = abilityNm;
		this.abilityType = abilityType;
		this.sysUpdtrId = sysUpdtrId;
		this.sysSvcId = sysSvcId;
		this.sysCretDate = sysCretDate;
		this.sysUpdtDate = sysUpdtDate;
	}
    
    public EmpAbilityInfoDTO(EmpAbilityInfo empAbilityInfo) {
		this.empId = empAbilityInfo.getEmpAbilityInfoPk().getEmpId();
		this.abilityNm = empAbilityInfo.getEmpAbilityInfoPk().getAbilityNm();
		this.abilityType = empAbilityInfo.getAbilityType();
		this.sysUpdtrId = empAbilityInfo.getSysUpdtrId();
		this.sysSvcId = empAbilityInfo.getSysSvcId();
		this.sysCretDate = empAbilityInfo.getSysCretDate();
		this.sysUpdtDate = empAbilityInfo.getSysUpdtDate();
    }
}
