package com.rnb.profmng.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
    
    
    
//    public int update(ProjectDTO projectDto) {
//        String sql = "UPDATE PROJECT_TABLE SET "
//        		+ "PROJECT_CD = NVL(?, PROJECT_CD),"
//        		+ "PROJECT_NM = NVL(?, PROJECT_NM),"
//        		+ "START_DATE = NVL(?, START_DATE),"
//        		+ "END_DATE = NVL(?, END_DATE),"
//        		+ "PM_ID = NVL(?, PM_ID),"
//        		+ "CLIENT = NVL(?, CLIENT),"
//        		+ "CONTRACTOR = NVL(?, CONTRACTOR),"
//        		+ "MAN_MONTH = NVL(?, MAN_MONTH),"
//        		+ "TOT_AMT = NVL(?, TOT_AMT),"
//        		+ "PROJECT_TYPE = NVL(?, PROJECT_TYPE),"
//        		+ "UPDATE_DATE = ?"
//        		+ " WHERE PROJECT_CD = ?";
//
//        System.out.println(projectDto);
//        System.out.println(projectDto.getStartDate());
//        int result = jdbcTemplate.update(sql, 
//        		projectDto.getProjectCd(),
//        		projectDto.getProjectNm(),
//        		projectDto.getStartDate(),
//        		projectDto.getEndDate(),
//        		projectDto.getPmId(),
//        		projectDto.getClient(),
//        		projectDto.getContractor(),
//        		projectDto.getManMonth(),
//        		projectDto.getTotAmt(),
//        		projectDto.getProjectType(),
//        		LocalDate.now(),
//        		projectDto.getProjectCd());
//        
//        return result;
//    }
//    
//    public int delete(String projectCd) {
//        String sql = "DELETE FROM PROJECT_TABLE WHERE PROJECT_CD = ?";
//		
//        int result = jdbcTemplate.update(sql, projectCd);
//        
//        if (result > 0) {
//            System.out.println("삭제 성공!");
//        } else {
//            System.out.println("삭제 대상 없음.");
//        }
//        
//        return result;
//    }
}
