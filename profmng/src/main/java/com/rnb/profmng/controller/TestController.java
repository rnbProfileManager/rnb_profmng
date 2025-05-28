package com.rnb.profmng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rnb.profmng.repository.dto.TestDto;
import com.rnb.profmng.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
	private final TestService service;
	
	@GetMapping("/getUser")
	public TestDto getUser (@RequestBody TestDto dto) {
		TestDto outDto = service.getTest(dto);
		return outDto;
	}
	

	
}
