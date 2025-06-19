package com.rnb.profmng.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.dto.ProfiledbDto;
import com.rnb.profmng.entity.EmpAbilityInfoEntity;
import com.rnb.profmng.entity.EmpAbilityInfoId;

public interface ProfileRepository extends JpaRepository<EmpAbilityInfoEntity, EmpAbilityInfoId> {

//	@Query("SELECT new com.rnb.profmng.dto.ProfiledbDto(a.empCd, a.abilityType, a.abilityNm, b.jobTitle, b.callNumber, c.filePath , c.fileName) "
//			+ "FROM EmpAbilityInfoEntity a " + "JOIN EmpBasEntity b ON a.empCd = b.empCd "
//			+ "LEFT JOIN ProfileFileInfoEntity c ON a.empCd = c.empCd ")
//	List<ProfiledbDto> findAllProfiles();

	@Transactional
	@Query(
	    "SELECT new com.rnb.profmng.dto.ProfiledbDto(" +
	    "a.empBasPk.empId, " +
	    "a.empNm, " +
	    "a.jobGradeCd, " +
	    "a.jobTitleCd, " +
	    "a.homeAddr, " +
	    "a.callNumber, " +
	    "a.orgCd, " +
	    "a.empTypeCd, " +
	    "b.abilityType, " +
	    "b.empAbilityInfoPk.abilityNm, " +
	    "c.pjtHmnResrcInfoPk.pjtSeq, " +
	    "c.userRoleCd, " +
	    "d.fileNm, " +
	    "d.fileDir) " +
	    "FROM EmpBas a " +
	    "JOIN EmpAbilityInfo b ON a.empBasPk.empId = :empId " +
	    "AND a.empBasPk.empId = b.empAbilityInfoPk.empId " +
	    "JOIN PjtHmnResrcInfo c ON a.empBasPk.empId = c.pjtHmnResrcInfoPk.empId " + 
	    "LEFT JOIN ProfileFileInfoEntity d ON a.empBasPk.empId = d.empId"
	)
	List<ProfiledbDto> searchProfiles(@Param("empId") String empId);

	@Transactional
	@Query(
	    "SELECT new com.rnb.profmng.dto.ProfiledbDto(" +
	    "a.empBasPk.empId, " +
	    "a.empNm, " +
	    "a.jobGradeCd, " +
	    "a.jobTitleCd, " +
	    "a.homeAddr, " +
	    "a.callNumber, " +
	    "a.orgCd, " +
	    "a.empTypeCd, " +
	    "b.abilityType, " +
	    "b.empAbilityInfoPk.abilityNm, " +
	    "c.pjtHmnResrcInfoPk.pjtSeq, " +
	    "c.userRoleCd, " +
	    "d.fileNm, " +
	    "d.fileDir) " +
	    "FROM EmpBas a " +
	    "JOIN EmpAbilityInfo b ON a.empBasPk.empId = b.empAbilityInfoPk.empId " +
	    "JOIN PjtHmnResrcInfo c ON a.empBasPk.empId = c.pjtHmnResrcInfoPk.empId " + 
	    "LEFT JOIN ProfileFileInfoEntity d ON a.empBasPk.empId = d.empId"
	)
	List<ProfiledbDto> getAllProfiles();

}
