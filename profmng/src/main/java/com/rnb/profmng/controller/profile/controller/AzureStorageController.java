package com.rnb.profmng.controller.profile.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rnb.profmng.service.AzureStorageService;

import lombok.RequiredArgsConstructor;

/**
 * Azure Blob Storage를 통한 파일 업로드 및 다운로드를 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile/storage")
public class AzureStorageController {

    private final AzureStorageService azureStorageService;

    /**
     * 파일 업로드 API
     * @throws Exception 
     */
    @PostMapping("/uploadProfileFile")
    public String uploadProfileFile(
            @RequestParam("empCd") String empCd,
            @RequestParam("empNm") String empNm,
            @RequestParam("file") MultipartFile file) {

        // 서비스에 empCd 전달
        String uploadedFileName = azureStorageService.uploadProfileFile(empCd, empNm,file);
        return uploadedFileName;
    }

    /**
     * 파일 다운로드 API
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        return azureStorageService.downloadFile(fileName);
    }

    /**
     * 파일 리스트 조회 API
     */
    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        return azureStorageService.listFiles();
    }
} 