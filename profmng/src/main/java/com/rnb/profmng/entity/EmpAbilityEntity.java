package com.rnb.profmng.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMP_ABILITY_INFO")
@IdClass(EmpAbilityId.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmpAbilityEntity {

    // 사원코드
    @Id
    @Column(name = "emp_id", length = 20)
    private String empCd;

    // 직무능력형태 (자격증, 교육, 기술 등)
    @Column(name = "ability_type", length = 20)
    private String abilityType;

    // 직무능력명 (예: 정보처리기사, JAVA 등)
    @Column(name = "ability_nm", length = 255)
    private String abilityNm;

    // 변경자 아이디
    @Column(name = "sys_updtr_id", length = 20)
    private String sysUpdtrIid;

    // 서비스 아이디
    @Column(name = "sys_svc_id", length = 20)
    private String sysSvcId;

    // 생성 일자 (PK 일부)
    @Id
    @Column(name = "sys_cret_date")
    private LocalDateTime startDate;

    // 만료 일자
    @Column(name = "sys_updt_date")
    private LocalDateTime endDate;

    // 최종수정일자
    //@Column(name = "UPDATE_DATE")
    //private LocalDateTime updateDate;
}
