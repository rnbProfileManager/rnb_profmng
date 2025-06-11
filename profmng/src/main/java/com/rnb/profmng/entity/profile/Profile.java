package com.rnb.profmng.entity.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
	
    private EmpNo empNo;

    private EmpAbility empAbility;

    private ProjectEmpInfo projectEmpInfo;

}