package com.rnb.profmng.service.user;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rnb.profmng.dto.UserDTO;
import com.rnb.profmng.entity.user.User;
import com.rnb.profmng.repository.user.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// 로그인
	@Transactional(readOnly = true)
	public Optional<UserDTO> login(UserDTO requestUserDto) {
		Optional<User> loginUser = userRepository.findByUserIdAndUserPwd(requestUserDto.getUserId()
																													   , requestUserDto.getUserPwd());
		// 엔티티를 DTO로 변환하여 반환
		if( loginUser.isPresent()) {
			return Optional.of(new UserDTO(loginUser.get()));
		} else {
			return Optional.empty();
		}
	}
	
}
