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
public class EmpAbilityPK implements Serializable {
    @Column(name = "emp_id", length = 4, nullable = false)
    private String empCd;

    //@Column(name = "EMP_NM", length = 20, nullable = false)
    //private String empNm;

    @Column(name = "ability_type", length = 20, nullable = false)
    private String abilityType;

    @Column(name = "ability_nm", length = 255)
    private String abilityNm;
    
    @Column(name = "sys_cret_date", nullable = false)
    private LocalDate startDate;
}