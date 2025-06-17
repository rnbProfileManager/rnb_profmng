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
public class EmpNo {
	
    @EmbeddedId
    private EmpNoPK empNoPk;
    
    public String getEmpCd() { return empNoPk != null ? empNoPk.getEmpCd() : null; }
    public String getEmpNm() { return empNoPk != null ? empNoPk.getEmpNm() : null; }
    public LocalDate getStartDate() { return empNoPk != null ? empNoPk.getStartDate() : null; }
    
    @Column(name = "efct_end_date")
    private LocalDate endDate;

    //@Column(name = "sys_updt_date")
    //private LocalDate updateDate;

    @Column(name = "job_grade", length = 10)
    private String jobGrade;

    @Column(name = "job_title", length = 10)
    private String jobTitle;

    @Column(name = "home_addr", length = 100)
    private String address;
    
    @Column(name = "call_number", length = 15)
    private String callNumber;

    @Column(name = "org_nm", length = 30)
    private String orgNm;

    @Column(name = "emp_type_cd", length = 20)
    private String empType;
}