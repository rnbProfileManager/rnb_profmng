package com.rnb.profmng.entity.profile;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpAbilityInfoPK implements Serializable {
    
	// 사원 아이디
	@Column(name = "emp_id", length = 20, nullable = false)
    private String empId;

	// 직무능력 명
    @Column(name = "ability_nm", length = 255)
    private String abilityNm;
}