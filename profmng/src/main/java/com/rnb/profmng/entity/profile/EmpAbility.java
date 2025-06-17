package com.rnb.profmng.entity.profile;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMP_ABILITY_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpAbility {
	
    @EmbeddedId
    private EmpAbilityPK empAbilityPk;
    
    public String getEmpCd() { return empAbilityPk != null ? empAbilityPk.getEmpCd() : null; }
    //public String getEmpNm() { return empAbilityPk != null ? empAbilityPk.getEmpNm() : null; }
    public String getAbilityType() { return empAbilityPk != null ? empAbilityPk.getAbilityType() : null; }
    public String getAbilityNm() { return empAbilityPk != null ? empAbilityPk.getAbilityNm() : null; }
    public LocalDate getStartDate() { return empAbilityPk != null ? empAbilityPk.getStartDate() : null; }

    @Column(name = "sys_updtr_id", length = 20)
    private String sysUpdtrId;

    @Column(name = "sys_svc_id", length = 20)
    private String sysSvcId;

    //@Column(name = "END_DATE")
    //private LocalDate endDate;

    @Column(name = "sys_updt_date")
    private LocalDate updateDate;
}