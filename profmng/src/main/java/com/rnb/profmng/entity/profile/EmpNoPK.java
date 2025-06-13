package com.rnb.profmng.entity.profile;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmpNoPK implements Serializable {
    @Column(name = "EMP_CD", length = 4, nullable = false)
    private String empCd;

    @Column(name = "EMP_NM", length = 10, nullable = false)
    private String empNm;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;
}