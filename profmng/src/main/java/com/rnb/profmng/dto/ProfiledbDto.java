package com.rnb.profmng.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProfiledbDto {
	
	//EMP_BAS
    private String empId;
    private String empNm;
    private String jobGradeCd;
    private String jobTitleCd;
    private String homeAddr;
    private String callNumber;
    private String orgCd;
    private String empTypeCd;
    
    //EMP_ABILITY_INFO
    private String abilityType;
    private String abilityNm;
    
    //PJT_HMN_RESRC_INFO
    private long pjtSeq;
    private String userRoleCd;
    
    private String fileNm;
    private String fileDir;
}
