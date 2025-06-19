package com.rnb.profmng.dto.profile;

import java.time.LocalDate;

import com.rnb.profmng.entity.profile.PjtHmnResrcInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
    
	public PjtHmnResrcInfoDTO(long pjtSeq, String empId, LocalDate efctStartDate, LocalDate efctEndDate,
			String sysUpdtrId, String sysSvcId, LocalDate sysCretDate, LocalDate sysUpdtDate, String userRoleCd) {
		this.pjtSeq = pjtSeq;
		this.empId = empId;
		this.efctStartDate = efctStartDate;
		this.efctEndDate = efctEndDate;
		this.sysUpdtrId = sysUpdtrId;
		this.sysSvcId = sysSvcId;
		this.sysCretDate = sysCretDate;
		this.sysUpdtDate = sysUpdtDate;
		this.userRoleCd = userRoleCd;
	}
    
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
