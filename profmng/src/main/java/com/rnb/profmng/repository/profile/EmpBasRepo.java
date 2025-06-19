package com.rnb.profmng.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.entity.profile.EmpBas;
import com.rnb.profmng.entity.profile.EmpBasPK;

@Repository
public interface EmpBasRepo extends JpaRepository<EmpBas, EmpBasPK>{
	
	// 전체 검색
    List<EmpBas> findAll();
    
    // 특정 코드 조회
    List<EmpBas> findByEmpBasPk_EmpId(String empId);

    // 직원 정보 만료
    @Modifying
    @Transactional
    @Query("DELETE FROM EmpBas p WHERE p.empBasPk.empId = :empId")
    int deleteByEmpId(@Param("empId") String empId);
}
