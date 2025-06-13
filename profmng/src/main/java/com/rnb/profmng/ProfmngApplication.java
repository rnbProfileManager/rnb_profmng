package com.rnb.profmng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rnb.profmng.repository")
@EntityScan(basePackages = "com.rnb.profmng.entity")
@ServletComponentScan
public class ProfmngApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfmngApplication.class, args);
    }
}