package com.rnb.profmng.dto;

import java.time.LocalDateTime;

import com.rnb.profmng.entity.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    
	private String userId;
    private String userPwd;
    private String userNm;
    private String userCd;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // --- 로그인 요청을 위한 생성자 ---
    public UserDTO(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }
    
    // User 엔티티를 DTO로 변환하기 위한 생성자( 응답 )
    public UserDTO(User user) {
    	this.userId = user.getUserId();
    	this.userPwd = user.getUserPwd();
    	this.userNm = user.getUserNm();
    	this.userCd = user.getUserCd();
    	this.createDate = user.getCreateDate();
    	this.updateDate = user.getUpdateDate();
    }
}
