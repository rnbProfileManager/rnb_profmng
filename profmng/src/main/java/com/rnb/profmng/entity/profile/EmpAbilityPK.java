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
    @Column(name = "EMP_CD", length = 4, nullable = false)
    private String empCd;

    @Column(name = "EMP_NM", length = 20, nullable = false)
    private String empNm;

    @Column(name = "ABILITY_TYPE", length = 20, nullable = false)
    private String abilityType;

    @Column(name = "ABILITY_NM", length = 1000)
    private String abilityNm;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;
}