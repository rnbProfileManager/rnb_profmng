package com.rnb.profmng.entity.profile;

import com.rnb.profmng.entity.project.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
	
    private EmpBas empBas;

    private EmpAbilityInfo empAbilityInfo;

    private PjtHmnResrcInfo pjtHmnResrcInfo;
    
    private Project project;
}