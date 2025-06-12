package com.rnb.profmng.entity.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

	@EmbeddedId
    private ProjectPK projectPk;
	
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

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