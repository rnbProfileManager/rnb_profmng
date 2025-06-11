package com.rnb.profmng.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileId implements Serializable {

    private String projectCd;
    private String empNo;
    private LocalDate updateDt;
}