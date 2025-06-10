package com.rnb.profmng.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rnb.profmng.dto.ProjectDTO;

import lombok.experimental.var;

@Repository
public class ProjectRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * 대시보드
     * select 기능
     */
	public List<ProjectDTO> select() {
        String sql = "SELECT * FROM (SELECT * FROM project_table WHERE 1=1 ORDER BY END_DATE DESC) WHERE rownum <= 4";
        		
        RowMapper<ProjectDTO> rowMapper = (rs, rowNum) -> {
        	ProjectDTO projectDto = new ProjectDTO();
        	projectDto.setProjectCd(rs.getString("PROJECT_CD"));
        	projectDto.setProjectNm(rs.getString("PROJECT_NM"));
        	
        	Date startDate = rs.getDate("START_DATE");
        	projectDto.setStartDate(startDate != null ? startDate.toLocalDate() : null);
        	Date endDate = rs.getDate("END_DATE");
        	projectDto.setEndDate(endDate != null ? endDate.toLocalDate() : null);
            return projectDto;
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
	
	public List<ProjectDTO> selectAll() {
        String sql = "SELECT * FROM project_table WHERE 1=1";
        		
        RowMapper<ProjectDTO> rowMapper = (rs, rowNum) -> {
        	ProjectDTO projectDto = new ProjectDTO();
        	projectDto.setProjectCd(rs.getString("PROJECT_CD"));
        	projectDto.setProjectNm(rs.getString("PROJECT_NM"));
            return projectDto;
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
    
    /*
     * 프로젝트 관리
     * select, insert, delete, update 기능
     */
    public int insert(ProjectDTO projectDto) {
        String sql = "INSERT INTO PROJECT_TABLE (project_cd, project_nm, start_date, end_date, pm_id, client, contractor, man_month, tot_amt, project_type) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int result = jdbcTemplate.update(sql, projectDto.getProjectCd(),
        		projectDto.getProjectNm(),
        		projectDto.getStartDate(),
        		projectDto.getEndDate(),
        		projectDto.getPmId(),
        		projectDto.getClient(),
        		projectDto.getContractor(),
        		projectDto.getManMonth(),
        		projectDto.getTotAmt(),
        		projectDto.getProjectType());
        
        return result;
    }
    
    @SuppressWarnings("deprecation")
	public List<ProjectDTO> select(String projectCd) {
        String sql = "SELECT * FROM PROJECT_TABLE WHERE PROJECT_CD = ?";
        		
        RowMapper<ProjectDTO> rowMapper = (rs, rowNum) -> {
        	ProjectDTO projectDto = new ProjectDTO();
        	projectDto.setProjectCd(rs.getString("PROJECT_CD"));
        	projectDto.setProjectNm(rs.getString("PROJECT_NM"));
        	
        	Date startDate = rs.getDate("START_DATE");
        	Date endDate = rs.getDate("END_DATE");
        	
        	projectDto.setStartDate(startDate != null ? startDate.toLocalDate() : null);
        	projectDto.setEndDate(endDate != null ? endDate.toLocalDate() : null);
        	projectDto.setPmId(rs.getString("PM_ID"));
        	projectDto.setClient(rs.getString("CLIENT"));
        	projectDto.setContractor(rs.getString("CONTRACTOR"));
        	projectDto.setManMonth(rs.getBigDecimal("MAN_MONTH"));
        	projectDto.setTotAmt(rs.getBigDecimal("TOT_AMT"));
        	projectDto.setProjectType(rs.getString("PROJECT_TYPE"));
            return projectDto;
        };

        return jdbcTemplate.query(sql, new Object[]{projectCd}, rowMapper);
    }
    
    public int update(ProjectDTO projectDto) {
        String sql = "UPDATE PROJECT_TABLE SET "
        		+ "PROJECT_CD = NVL(?, PROJECT_CD),"
        		+ "PROJECT_NM = NVL(?, PROJECT_NM),"
        		+ "START_DATE = NVL(?, START_DATE),"
        		+ "END_DATE = NVL(?, END_DATE),"
        		+ "PM_ID = NVL(?, PM_ID),"
        		+ "CLIENT = NVL(?, CLIENT),"
        		+ "CONTRACTOR = NVL(?, CONTRACTOR),"
        		+ "MAN_MONTH = NVL(?, MAN_MONTH),"
        		+ "TOT_AMT = NVL(?, TOT_AMT),"
        		+ "PROJECT_TYPE = NVL(?, PROJECT_TYPE),"
        		+ "UPDATE_DATE = ?"
        		+ " WHERE PROJECT_CD = ?";

        System.out.println(projectDto);
        System.out.println(projectDto.getStartDate());
        int result = jdbcTemplate.update(sql, 
        		projectDto.getProjectCd(),
        		projectDto.getProjectNm(),
        		projectDto.getStartDate(),
        		projectDto.getEndDate(),
        		projectDto.getPmId(),
        		projectDto.getClient(),
        		projectDto.getContractor(),
        		projectDto.getManMonth(),
        		projectDto.getTotAmt(),
        		projectDto.getProjectType(),
        		LocalDate.now(),
        		projectDto.getProjectCd());
        
        return result;
    }
    
    public int delete(String projectCd) {
        String sql = "DELETE FROM PROJECT_TABLE WHERE PROJECT_CD = ?";
		
        int result = jdbcTemplate.update(sql, projectCd);
        
        if (result > 0) {
            System.out.println("삭제 성공!");
        } else {
            System.out.println("삭제 대상 없음.");
        }
        
        return result;
    }
}
