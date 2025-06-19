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
@Table(name = "PJT_HMN_RESRC_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PjtHmnResrcInfo {
	
    @EmbeddedId
    private PjtHmnResrcInfoPK pjtHmnResrcInfoPk;
    
    public long getPjtSeq() { return pjtHmnResrcInfoPk != null ? pjtHmnResrcInfoPk.getPjtSeq() : null; }
    public String getEmpId() { return pjtHmnResrcInfoPk != null ? pjtHmnResrcInfoPk.getEmpId() : null; }
    public LocalDate getEfctStartDate() { return pjtHmnResrcInfoPk != null ? pjtHmnResrcInfoPk.getEfctStartDate() : null; }

    @Column(name = "efct_end_date")
    private LocalDate efctEndDate;

    @Column(name = "sys_updtr_id", length = 20)
    private String sysUpdtrId;

    @Column(name = "sys_svc_id", length = 20)
    private String sysSvcId;

    @Column(name = "sys_cret_date")
    private LocalDate sysCretDate;

    @Column(name = "sys_updt_date")
    private LocalDate sysUpdtDate;

    @Column(name = "user_role_cd", length = 20)
    private String userRoleCd;
}