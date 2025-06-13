package com.rnb.profmng.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.entity.profile.EmpNo;
import com.rnb.profmng.entity.profile.EmpNoPK;

@Repository
public interface EmpNoRepo extends JpaRepository<EmpNo, EmpNoPK>{
	
	// 전체 검색
    List<EmpNo> findAll();
    
    // 특정 코드 조회
    List<EmpNo> findByEmpNoPk_EmpCd(String empCd);

    // 직원 정보 만료
    // TODO delete가 아니라 만료 일자를 변경하는걸로 바꿔야 함
    @Modifying
    @Transactional
    @Query("DELETE FROM EmpNo p WHERE p.empNoPk.empCd = :empCd")
    int deleteByEmpCd(@Param("empCd") String empCd);
}
