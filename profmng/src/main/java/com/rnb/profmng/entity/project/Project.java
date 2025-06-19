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
	
	public long getPjtSeq() { return projectPk != null ? projectPk.getPjtSeq() : null; }
    public String getPjtNm() { return projectPk != null ? projectPk.getPjtNm() : null; }
    public LocalDate getEfctStartDate() { return projectPk != null ? projectPk.getEfctStartDate() : null; }
	
    @Column(name = "efct_end_date")
    private LocalDate efctEndDate;

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
    private String pjtTypeCd;

}