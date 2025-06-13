package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

public interface EmpNoView {
    String getEmpCd();
    String getEmpNm();
    LocalDate getStartDate();
    LocalDate getEndDate();
    LocalDate getUpdateDate();
    String getJobGrade();
    String getJobTitle();
    String getAddress();
    String getCallNumber();
    String getOrgNm();
    String getEmpType();
}

