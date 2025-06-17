package com.rnb.profmng.entity.project;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PJT_BAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

	@EmbeddedId
    private ProjectPK projectPk;
	
	public String getProjectCd() { return projectPk != null ? projectPk.getProjectCd() : null; }
    public String getProjectNm() { return projectPk != null ? projectPk.getProjectNm() : null; }
    public LocalDate getStartDate() { return projectPk != null ? projectPk.getStartDate() : null; }
	
    @Column(name = "efct_end_date")
    private LocalDate endDate;

    //@Column(name = "sys_updt_date")
    //private LocalDate updateDate;

    @Column(name = "pm_id", length = 20)
    private String pmId;

    @Column(name = "client", length = 20)
    private String client;

    @Column(name = "contractor", length = 20)
    private String contractor;
    
    @Column(name = "mm_qty", precision = 20, scale = 2)
    private BigDecimal manMonth;

    @Column(name = "tot_amt", precision = 20)
    private BigDecimal totAmt;

    @Column(name = "pjt_type_cd", length = 10)
    private String projectType;

}