package com.rnb.profmng.service;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.rnb.profmng.entity.ProfileFileInfoEntity;
import com.rnb.profmng.repository.ProfileFileInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AzureStorageService {

	 private final BlobContainerClient blobContainerClient;
	    private final ProfileFileInfoRepository profileFileInfoRepository;

	    public String uploadProfileFile(String empCd, String empNm, MultipartFile file) {

	    	
	    	 String fileName = file.getOriginalFilename();
	    	 String ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
	    	    // 파일 확장자 추출
	    	    if (fileName == null || !ext.equals("doc") && !ext.equals("docx")) {
	    	        return "doc 또는 docx 파일만 업로드 가능합니다.";
	    	    }

	    	
	        String today = LocalDate.now().toString().replace("-", "");
	        String fileNames = "알앤비_" + empNm + "_" + today + "."+ ext;
	        String filePath = "/pod/" + fileNames;
	        try {
	        	// Azure Blob Upload
		        BlobClient blobClient = blobContainerClient.getBlobClient(filePath);
		        blobClient.upload(file.getInputStream(), file.getSize(), true);
	        }catch (Exception e) {
	        	return "Azure 파일등록 실패";
			}
	        try {
	        	// DB Insert
		        ProfileFileInfoEntity entity = new ProfileFileInfoEntity();
		        entity.setEmpId(empCd);
		        entity.setFileNm(fileNames);
		        entity.setFileDir(filePath);
		        entity.setFileSize(file.getSize());
		        entity.setUploadDt(LocalDateTime.now());

		        profileFileInfoRepository.save(entity);

	        }catch (Exception e) {
	        	return "DB저장 실패";
			}
	        	        
	        return null;
	    }
	    

	    
    /**
     * 파일 다운로드 처리
     */
    public ResponseEntity<byte[]> downloadFile(String fileName) {
        try {
            BlockBlobClient blobClient = blobContainerClient.getBlobClient(fileName).getBlockBlobClient();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            blobClient.download(outputStream);

            byte[] content = outputStream.toByteArray();
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentLength(content.length);
            headers.setContentDispositionFormData("attachment", encodedFileName);

            return new ResponseEntity<>(content, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * 파일 목록 조회 처리
     */
    public ResponseEntity<List<String>> listFiles() {
        List<String> fileNames = new ArrayList<>();
        for (BlobItem blobItem : blobContainerClient.listBlobs()) {
            fileNames.add(blobItem.getName());
        }
        return ResponseEntity.ok(fileNames);
    }
}