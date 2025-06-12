package com.rnb.profmng.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.entity.project.ProjectPK;

@Repository
public interface ProjectRepo extends JpaRepository<Project, ProjectPK>{
	
	// 전체 검색
    List<Project> findAll();

    // 특정 문자열 검색
    List<Project> findByProjectPk_ProjectCdContaining(String projectCd);
    
    // ProjectCd 기준 Update
    Optional<Project> findByProjectPk_ProjectCd(String projectCd);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Project p WHERE p.projectPk.projectCd = :projectCd")
    int deleteByProjectCd(@Param("projectCd") String projectCd);
    
}
