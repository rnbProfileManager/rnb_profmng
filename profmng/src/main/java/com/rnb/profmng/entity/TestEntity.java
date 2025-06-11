package com.rnb.profmng.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table (name = "test_table")
@Entity
@Data
public class TestEntity {
	@Id
	@Column(name = "TEST_USER", length = 50, nullable = false)
	private String testUser;
	@Column(name = "TEST_TEXT", length = 255)
	private String testText;
	
	public String getTestUser() {
        return testUser;
    }

    public String getTestText() {
        return testText;
    }

}
