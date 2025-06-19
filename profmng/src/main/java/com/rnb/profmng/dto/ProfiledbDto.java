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
	
    private String empCd;
    private String empId;
    //private String empNm;
    private String abilityType;
    private String abilityNm;
    private String jobTitle;
    private String callNumber;
    private String filePath;
    private String fileName;

}
