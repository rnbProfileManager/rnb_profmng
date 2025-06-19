package com.rnb.profmng.entity.project;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.rnb.profmng.entity.profile.EmpBasPK;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProjectPK implements Serializable {
    @Column(name = "pjt_seq", nullable = false)
    private long pjtSeq;

    @Column(name = "pjt_nm", length = 255, nullable = false)
    private String pjtNm;

    @Column(name = "efct_start_date", nullable = false)
    private LocalDate efctStartDate;
}