package com.rnb.profmng.entity.project;

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
public class ProjectPK implements Serializable {
    @Column(name = "PROJECT_CD", length = 4, nullable = false)
    private String projectCd;

    @Column(name = "PROJECT_NM", length = 100, nullable = false)
    private String projectNm;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;
}