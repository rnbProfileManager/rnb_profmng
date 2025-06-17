package com.rnb.profmng.entity.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_BAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", length = 255, nullable = false)
    private String userId;

    @Column(name = "user_pwd", length = 255, nullable = false)
    private String userPwd;

    @Column(name = "emp_nm", length = 20)
    private String userNm;

    //@Column(name = "user_cd", length = 4)
    //private String userCd;

    @Column(name = "sys_updtr_id", length = 20)
    private String sysUpdtrId;

    @Column(name = "sys_svc_id", length = 20)
    private String sysSvcId;

    @Column(name = "sys_cret_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "sys_updt_date")
    private LocalDateTime updateDate;
}