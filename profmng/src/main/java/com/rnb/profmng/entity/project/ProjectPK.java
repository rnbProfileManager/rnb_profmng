package com.rnb.profmng.entity.project;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
//    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDateTime startDate;
    
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