package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.EmpAbilityInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpAbilityInfoDTO{
    
	private String empId;
    private String abilityNm;
    
    private String abilityType;
    private String sysUpdtrId;
    private String sysSvcId;
    private LocalDate sysCretDate;
    private LocalDate sysUpdtDate;
    
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
