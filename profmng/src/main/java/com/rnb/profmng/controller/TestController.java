package com.rnb.profmng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rnb.profmng.entity.TestEntity;
import com.rnb.profmng.service.TestService;

@Controller
@RequestMapping("/api")
public class TestController {
	
	private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    // 실제 데이터를 가져와 Model에 담고 JSP 뷰를 반환하는 메서드
    // URL: /test/detail/{testUserValue} (예: /test/detail/RNB003)
    @GetMapping("/detail/{testUserValue}")
    public String getTestDetailWithModel(@PathVariable("testUserValue") String testUserValue, Model model) {
        System.out.println("TestController 상세 페이지 호출: testUserValue = " + testUserValue);

        // Service 계층을 통해 DB에서 데이터 조회
        TestEntity testEntity = testService.getTestEntityByTestUser(testUserValue);

        // 조회된 데이터를 Model에 담아 JSP로 전달
        model.addAttribute("testUser", testEntity.getTestUser());
        model.addAttribute("testText", testEntity.getTestText());
        model.addAttribute("entityData", testEntity); // 엔티티 객체 자체를 넘겨줄 수도 있음
        
        System.out.println("DB에서 조회된 데이터: " + testEntity.getTestUser() + ", " + testEntity.getTestText());
        
        // "/WEB-INF/views/user_detail.jsp" 뷰를 반환 (prefix/suffix 설정에 따라)
        return "/user/user_detail"; // user_detail.jsp 파일을 찾아 렌더링
    }
}
