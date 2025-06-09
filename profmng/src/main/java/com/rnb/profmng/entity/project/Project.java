package com.rnb.profmng.entity.project;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROJECT_TABLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @Column(name = "PROJECT_CD", length = 4, nullable = false)
    private String projectCd;

    @Column(name = "PROJECT_NM", length = 100, nullable = false)
    private String projectNm;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "PM_ID", length = 20)
    private String pmId;

    @Column(name = "CLIENT", length = 20)
    private String client;

    @Column(name = "CONTRACTOR", length = 20)
    private String contractor;
    
    @Column(name = "MAN_MONTH", precision = 20, scale = 2)
    private BigDecimal manMonth;

    @Column(name = "TOT_AMT", precision = 20)
    private BigDecimal totAmt;

    @Column(name = "PROJECT_TYPE", length = 10)
    private String projectType;

}