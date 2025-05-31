package com.rnb.profmng.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnb.profmng.entity.TestEntity;
import com.rnb.profmng.repository.TestRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 사용 
@Service // TestService 빈 등록 
public class TestService {
	
	private final TestRepository testRepository;
	
	@Autowired // 생성자 주입
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    // testUser 값으로 TestEntity를 조회하는 메서드
    public TestEntity getTestEntityByTestUser(String testUser) {
        Optional<TestEntity> entityOptional = testRepository.findByTestUser(testUser);
        return entityOptional.orElse(null); // 찾지 못하면 null 반환
    }
}
