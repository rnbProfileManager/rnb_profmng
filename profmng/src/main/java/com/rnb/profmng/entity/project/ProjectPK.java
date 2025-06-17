package com.rnb.profmng.entity.project;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
    @Column(name = "pjt_cd", length = 4, nullable = false)
    private String projectCd;

    @Column(name = "pjt_nm", length = 255, nullable = false)
    private String projectNm;

    @Column(name = "efct_start_date", nullable = false)
    private LocalDate startDate;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectPK that = (ProjectPK) o;
        return projectCd.equals(that.projectCd) &&
               projectNm.equals(that.projectNm) &&
               startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCd, projectNm, startDate);
    }
}