package com.rnb.profmng.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rnb.profmng.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

 		Optional<User> findByUserIdAndUserPwd(String userId, String userPwd);
}