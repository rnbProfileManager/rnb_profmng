package com.rnb.profmng.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EMP_BAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpBasEntity {

    @Id
    @Column(name = "emp_id")
    private String empCd;

    @Column(name = "emp_nm")
    private String empNm;

    @Column(name = "efct_start_date")
    private LocalDateTime startDate;

    @Column(name = "efct_end_date")
    private LocalDateTime endDate;

    //@Column(name = "UPDATE_DATE")
    //private LocalDateTime updateDate;

    @Column(name = "job_grade_cd")
    private String jobGrade;

    @Column(name = "job_title_cd")
    private String jobTitle;

    @Column(name = "home_addr")
    private String address;

    @Column(name = "call_number")
    private String callNumber;

    @Column(name = "org_cd")
    private String orgNm;

    @Column(name = "emp_type_cd")
    private String empType;
}