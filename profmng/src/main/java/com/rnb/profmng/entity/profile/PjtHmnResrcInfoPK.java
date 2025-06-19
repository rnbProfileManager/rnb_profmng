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
public class PjtHmnResrcInfoPK implements Serializable {
    @Column(name = "pjt_seq", nullable = false)
    private long pjtSeq;

    @Column(name = "emp_id", length = 20, nullable = false)
    private String empId;

    @Column(name = "efct_start_date", nullable = false)
    private LocalDate efctStartDate;
}