package com.rnb.profmng.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureStorageConfig {

    // application.yml에서 불러올 값들
    @Value("${azure.storage.account-name}")
    private String accountName;

    @Value("${azure.storage.account-key}")
    private String accountKey;

    @Value("${azure.storage.container-name}")
    private String containerName;

    /**
     * Azure Blob 서비스 클라이언트를 생성합니다.
     */
    @Bean
    public BlobServiceClient blobServiceClient() {
        String endpoint = String.format("https://%s.blob.core.windows.net", accountName);

        // 계정 이름과 키로 인증 정보 구성
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);

        return new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(credential)
                .buildClient();
    }

    /**
     * 지정한 컨테이너 클라이언트를 생성
     */
    @Bean
    public BlobContainerClient blobContainerClient() {
        return blobServiceClient().getBlobContainerClient(containerName);
    }
}