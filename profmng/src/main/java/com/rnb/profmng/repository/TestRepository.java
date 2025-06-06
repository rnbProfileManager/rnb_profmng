package com.rnb.profmng.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rnb.profmng.entity.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, String>{	
	Optional<TestEntity> findByTestUser(String testUser);
}
