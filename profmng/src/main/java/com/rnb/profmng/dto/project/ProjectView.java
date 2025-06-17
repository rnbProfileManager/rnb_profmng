package com.rnb.profmng.dto.project;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ProjectView {
    String getProjectCd();
    String getProjectNm();
    LocalDate getStartDate();
    LocalDate getEndDate();
    //LocalDate getUpdateDate();
    String getPmId();
    String getClient();
    String getContractor();
    BigDecimal getManMonth();
    BigDecimal getTotAmt();
    String getProjectType();
}