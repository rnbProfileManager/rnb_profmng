package com.rnb.profmng.entity.profile;import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROJECT_EMP_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEmpInfo {
	
    @EmbeddedId
    private ProjectEmpInfoPK projectEmpInfoPk;
    
    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "UPDATE_DATE")
    private LocalDate updateDate;

    @Column(name = "USER_ROLE", length = 10)
    private String userRole;
}