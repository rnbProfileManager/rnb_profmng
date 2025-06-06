package com.rnb.profmng.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.dto.UserDTO;
import com.rnb.profmng.service.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// 로그인 페이지를 보여주는 GET 요청
    @GetMapping("/showLogin")
    public String showLoginPage() {
	        return "user/login";
    }
	
	// 로그인
    @PostMapping("/login")
    public String processLogin(@ModelAttribute UserDTO loginRequestDto,	// UserDTO 객체로 폼 데이터 바인딩
                               HttpSession session) {
    		
	    	Optional<UserDTO> authenticatedUserDto = userService.login(loginRequestDto);
	    	
	    	if (authenticatedUserDto.isPresent()) {
	    		UserDTO user = authenticatedUserDto.get();
	    		
	            session.setAttribute("loggedInUser", user.getUserId());
	            session.setAttribute("loggedInUserNm", user.getUserNm());
	
	            return "redirect:/index.html";
	        } else {
	            return "redirect:/login?error=true";
	        }
    }
    
    @GetMapping("/api/user/status")
    @ResponseBody
    public Map<String, Object> getUserStatus(HttpSession session) {
	        Map<String, Object> response = new HashMap<>();
	        String loggedInUser = (String) session.getAttribute("loggedInUser");
	        String loggedInUserNm = (String) session.getAttribute("loggedInUserNm");
	
	        if (loggedInUser != null) {
	            response.put("loggedIn", true);
	            response.put("loggedInUser", loggedInUser);
	            response.put("loggedInUserNm", loggedInUserNm);
	        } else {
	            response.put("loggedIn", false);
	            response.put("loggedInUser", null);
	            response.put("loggedInUserNm", null);
	        }
	        return response;
    }
    
    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return "redirect:/user/showLogin"; // 로그아웃 후 이동할 페이지
    }
}
