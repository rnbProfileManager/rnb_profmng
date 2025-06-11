package com.rnb.profmng.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "PROFILE_FILE_INFO")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProfileFileInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "EMP_CD")
    private String empCd;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @Column(name = "UPLOAD_DT")
    private LocalDateTime uploadDt;
}