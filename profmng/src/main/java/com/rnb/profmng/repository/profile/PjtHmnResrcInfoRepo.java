package com.rnb.profmng.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.entity.profile.PjtHmnResrcInfo;
import com.rnb.profmng.entity.profile.PjtHmnResrcInfoPK;

@Repository
public interface PjtHmnResrcInfoRepo extends JpaRepository<PjtHmnResrcInfo, PjtHmnResrcInfoPK>{
	
	// 전체 검색
    List<PjtHmnResrcInfo> findAll();
    
    // 특정 사원 코드 조회
    List<PjtHmnResrcInfo> findByPjtHmnResrcInfoPk_EmpId(String empId);

    // 투입 인력 만료
    @Modifying
    @Transactional
    @Query("DELETE FROM PjtHmnResrcInfo p WHERE p.pjtHmnResrcInfoPk.pjtSeq = :pjtSeq")
    int deleteByPjtHmnResrcInfoPK_PjtSeq(@Param("pjtSeq") String pjtSeq);
}
