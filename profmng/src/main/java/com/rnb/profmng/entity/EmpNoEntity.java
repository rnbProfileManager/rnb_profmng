package com.rnb.profmng.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EMP_NO")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmpNoEntity {

    @Id
    @Column(name = "EMP_CD")
    private String empCd;

    @Column(name = "EMP_NM")
    private String empNm;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "JOB_GRADE")
    private String jobGrade;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CALL_NUMBER")
    private String callNumber;

    @Column(name = "ORG_NM")
    private String orgNm;

    @Column(name = "EMP_TYPE")
    private String empType;
}