package com.rnb.profmng.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProjectView {
    String getProjectCd();
    String getProjectNm();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    LocalDateTime getUpdateDate();
    String getPmId();
    String getClient();
    String getContractor();
    BigDecimal getManMonth();
    BigDecimal getTotAmt();
    String getProjectType();
}