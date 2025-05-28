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
	@Column(name = "TEST_USER")
	private String testUser;
	@Column(name = "TEST_TEXT")
	private String testText;

}
