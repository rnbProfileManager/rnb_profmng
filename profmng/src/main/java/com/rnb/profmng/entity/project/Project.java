package com.rnb.profmng.entity.project;

import java.time.LocalDateTime;

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
    @Column(name = "project_Cd", length = 4, nullable = false)
    private String projectCd;

    @Column(name = "project_Nm", length = 100, nullable = false)
    private String projectNm;

    @Column(name = "start_Date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "endD_ate")
    private LocalDateTime endDate;

    @Column(name = "pm_Id", length = 20)
    private String pmId;

    @Column(name = "client", length = 20)
    private String client;

    @Column(name = "contractor", length = 20)
    private String contractor;
    
    @Column(name = "members", length = 20)
    private String members;

    @Column(name = "tot_Amt", length = 20)
    private String totAmt;

    @Column(name = "project_Type", length = 10)
    private String projectType;

}