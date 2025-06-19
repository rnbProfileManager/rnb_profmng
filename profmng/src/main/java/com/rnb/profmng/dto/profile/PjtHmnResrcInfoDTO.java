package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.PjtHmnResrcInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PjtHmnResrcInfoDTO{
    
	private long pjtSeq;
    private String empId;
    private LocalDate efctStartDate;
    
    private LocalDate efctEndDate;
    private String sysUpdtrId;
    private String sysSvcId;
    private LocalDate sysCretDate;
    private LocalDate sysUpdtDate;
    private String userRoleCd;
    
    public PjtHmnResrcInfoDTO(PjtHmnResrcInfo pjtHmnResrcInfo) {
    	this.pjtSeq = pjtHmnResrcInfo.getPjtHmnResrcInfoPk().getPjtSeq();
    	this.empId = pjtHmnResrcInfo.getPjtHmnResrcInfoPk().getEmpId();
    	this.efctStartDate = pjtHmnResrcInfo.getPjtHmnResrcInfoPk().getEfctStartDate();
    	this.efctEndDate = pjtHmnResrcInfo.getEfctEndDate();
    	this.sysUpdtrId = pjtHmnResrcInfo.getSysUpdtrId();
    	this.sysSvcId = pjtHmnResrcInfo.getSysSvcId();
    	this.sysCretDate = pjtHmnResrcInfo.getSysCretDate();
    	this.sysUpdtDate = pjtHmnResrcInfo.getSysUpdtDate();
    	this.userRoleCd = pjtHmnResrcInfo.getUserRoleCd();
    }
}
