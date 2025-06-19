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
public class EmpAbilityInfo {
	
	// PK값
    @EmbeddedId
    private EmpAbilityInfoPK empAbilityInfoPk;
    
    // null값 update할 때 PK값 체크 
    public String getEmpId() { return empAbilityInfoPk != null ? empAbilityInfoPk.getEmpId() : null; }
    public String getAbilityNm() { return empAbilityInfoPk != null ? empAbilityInfoPk.getAbilityNm() : null; }

    // 직무능력 유형
    @Column(name = "ability_type", length = 20, nullable = false)
    private String abilityType;

    // 변경자 아이디
    @Column(name = "sys_updtr_id", length = 20)
    private String sysUpdtrId;

    // 서비스 아이디
    @Column(name = "sys_svc_id", length = 20)
    private String sysSvcId;

    // 생성 일자
    @Column(name = "sys_cret_date", nullable = false)
    private LocalDate sysCretDate;

    // 최근 수정 날짜
    @Column(name = "sys_updt_date")
    private LocalDate sysUpdtDate;
}