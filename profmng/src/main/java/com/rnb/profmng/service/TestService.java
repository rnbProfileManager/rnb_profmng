package com.rnb.profmng.service;

import org.springframework.stereotype.Service;

import com.rnb.profmng.entity.TestEntity;
import com.rnb.profmng.repository.TestRepository;
import com.rnb.profmng.repository.dto.TestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 사용 
@Service //빈 등록 
@RequiredArgsConstructor //생성자 주입  
public class TestService {
	private final TestRepository repo;
	
	public TestDto getTest (TestDto inDs) {
		TestEntity aa  = repo.findByTestUser(inDs.getInput());
		
		log.info(aa.toString());
		return TestDto.builder()
				.input(aa.getTestUser())
				.output(aa.getTestText())
				.build();
	}
}
