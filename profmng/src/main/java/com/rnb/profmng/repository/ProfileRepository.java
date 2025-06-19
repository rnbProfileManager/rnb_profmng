package com.rnb.profmng.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rnb.profmng.dto.ProfiledbDto;
import com.rnb.profmng.entity.EmpAbilityEntity;
import com.rnb.profmng.entity.EmpAbilityId;

public interface ProfileRepository extends JpaRepository<EmpAbilityEntity, EmpAbilityId> {

	@Query("SELECT new com.rnb.profmng.dto.ProfiledbDto(a.empCd, a.abilityType, a.abilityNm, b.jobTitle, b.callNumber, c.filePath , c.fileName) "
			+ "FROM EmpAbilityEntity a " + "JOIN EmpBasEntity b ON a.empCd = b.empCd "
			+ "LEFT JOIN ProfileFileInfoEntity c ON a.empCd = c.empCd ")
	List<ProfiledbDto> findAllProfiles();

	@Query("""
			SELECT new com.rnb.profmng.dto.ProfiledbDto(
			    a.empCd, a.abilityType, a.abilityNm,
			    b.jobTitle, b.callNumber,
			    c.filePath, c.fileName
			)
			FROM EmpAbilityEntity a
			JOIN EmpBasEntity b ON a.empCd = b.empCd
			LEFT JOIN ProfileFileInfoEntity c
			  ON a.empCd = c.empCd
			  AND c.uploadDt = (
			      SELECT MAX(c2.uploadDt)
			      FROM ProfileFileInfoEntity c2
			      WHERE c2.empCd = a.empCd
			  )
			WHERE (:startDate IS NULL OR a.startDate >= :startDate)
			  AND (:endDate IS NULL OR a.endDate <= :endDate)
			""")
			List<ProfiledbDto> searchProfiles(
			    @Param("empCd") String empCd,
			    @Param("startDate") LocalDateTime startDate,
			    @Param("endDate") LocalDateTime endDate
			);

	@Query("""
			SELECT new com.rnb.profmng.dto.ProfiledbDto(
			    a.empCd, a.abilityType, a.abilityNm, b.jobTitle, b.callNumber, c.filePath,c.fileName
			)
			FROM EmpAbilityEntity a
			JOIN EmpBasEntity b ON a.empCd = b.empCd
			LEFT JOIN ProfileFileInfoEntity c ON a.empCd = c.empCd AND c.uploadDt = (
			    SELECT MAX(c2.uploadDt) FROM ProfileFileInfoEntity c2 WHERE c2.empCd = a.empCd
			)
			""")
	List<ProfiledbDto> getAllProfiles();

}
