package com.rnb.profmng.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "PROFILE_FILE_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileFileInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_seq")
    private Long fileSeq;

    @Column(name = "emp_id")
    private String empId;

    @Column(name = "file_nm")
    private String fileNm;

    @Column(name = "file_dir")
    private String fileDir;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "sys_updt_date")
    private LocalDateTime uploadDt;
}