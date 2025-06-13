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
@Table(name = "EMP_NO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpNo {
	
    @EmbeddedId
    private EmpNoPK empNoPk;
    
    public String getEmpCd() { return empNoPk != null ? empNoPk.getEmpCd() : null; }
    public String getEmpNm() { return empNoPk != null ? empNoPk.getEmpNm() : null; }
    public LocalDate getStartDate() { return empNoPk != null ? empNoPk.getStartDate() : null; }
    
    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "UPDATE_DATE")
    private LocalDate updateDate;

    @Column(name = "JOB_GRADE", length = 10)
    private String jobGrade;

    @Column(name = "JOB_TITLE", length = 10)
    private String jobTitle;

    @Column(name = "ADDRESS", length = 100)
    private String address;
    
    @Column(name = "CALL_NUMBER", length = 15)
    private String callNumber;

    @Column(name = "ORG_NM", length = 30)
    private String orgNm;

    @Column(name = "EMP_TYPE", length = 20)
    private String empType;
}