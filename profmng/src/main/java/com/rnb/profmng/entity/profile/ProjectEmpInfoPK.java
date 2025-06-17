package com.rnb.profmng.entity.profile;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEmpInfoPK implements Serializable {
    @Column(name = "pjt_cd", length = 10, nullable = false)
    private String projectCd;

    @Column(name = "emp_id", length = 10, nullable = false)
    private String empCd;

    @Column(name = "pjt_nm", length = 20, nullable = false)
    private String projectNm;

    @Column(name = "efct_start_date", nullable = false)
    private LocalDate startDate;
}