package com.rnb.profmng.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EmpAbilityId implements Serializable {

    private static final long serialVersionUID = -5164275620127845543L;
	private String empCd;
    private String empNm;
    private String abilityNm;
    private LocalDateTime startDate;

    public EmpAbilityId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpAbilityId)) return false;
        EmpAbilityId that = (EmpAbilityId) o;
        return Objects.equals(empCd, that.empCd) &&
                Objects.equals(empNm, that.empNm) &&
                Objects.equals(abilityNm, that.abilityNm) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCd, empNm, abilityNm, startDate);
    }
}