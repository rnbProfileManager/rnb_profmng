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
    @Column(name = "PROJECT_CD", length = 10, nullable = false)
    private String projectCd;

    @Column(name = "EMP_CD", length = 10, nullable = false)
    private String empCd;

    @Column(name = "PROJECT_NM", length = 20, nullable = false)
    private String projectNm;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;
}