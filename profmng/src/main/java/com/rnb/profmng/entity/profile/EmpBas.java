package com.rnb.profmng.entity.profile;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMP_BAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpBas {
	
    @EmbeddedId
    private EmpBasPK empBasPk;
    
    public String getEmpId() { return empBasPk != null ? empBasPk.getEmpId() : null; }
    public LocalDate getEfctStartDate() { return empBasPk != null ? empBasPk.getEfctStartDate() : null; }
    
    @Column(name = "emp_nm", length = 20, nullable = false)
    private String empNm;

    @Column(name = "efct_end_date")
    private LocalDate efctEndDate;

    @Column(name = "job_grade_cd", length = 20)
    private String jobGradeCd;

    @Column(name = "job_title_cd", length = 20)
    private String jobTitleCd;

    @Column(name = "home_addr", length = 255)
    private String homeAddr;
    
    @Column(name = "call_number", length = 20)
    private String callNumber;

    @Column(name = "org_cd", length = 20)
    private String orgCd;

    @Column(name = "emp_type_cd", length = 20)
    private String empTypeCd;
}