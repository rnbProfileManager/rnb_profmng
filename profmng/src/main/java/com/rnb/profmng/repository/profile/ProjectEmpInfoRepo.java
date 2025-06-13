package com.rnb.profmng.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.entity.profile.ProjectEmpInfo;
import com.rnb.profmng.entity.profile.ProjectEmpInfoPK;

@Repository
public interface ProjectEmpInfoRepo extends JpaRepository<ProjectEmpInfo, ProjectEmpInfoPK>{
	
	// 전체 검색
    List<ProjectEmpInfo> findAll();
    
    // 특정 사원 코드 조회
    List<ProjectEmpInfo> findByProjectEmpInfoPk_EmpCd(String empCd);

    // 투입 인력 만료
    // TODO delete가 아니라 만료 일자를 변경하는걸로 바꿔야 함
    @Modifying
    @Transactional
    @Query("DELETE FROM ProjectEmpInfo p WHERE p.projectEmpInfoPk.empCd = :empCd")
    int deleteByEmpCd(@Param("empCd") String empCd);
}
