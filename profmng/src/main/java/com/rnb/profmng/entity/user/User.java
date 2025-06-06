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
@Table(name = "USER_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", length = 50, nullable = false)
    private String userId;

    @Column(name = "user_pwd", length = 200, nullable = false)
    private String userPwd;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "user_nm", length = 10)
    private String userNm;

    @Column(name = "user_cd", length = 4)
    private String userCd;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}