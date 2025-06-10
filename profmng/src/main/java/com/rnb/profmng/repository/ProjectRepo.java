package com.rnb.profmng.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    // SELECT 기능
    public void findUsersByUsername(String username) {
        String sql = "SELECT * FROM PROJECT_TABLE WHERE PROJECT_CD = ?";
    }
    
    public int update(ProjectDTO projectDto) {
        String sql = "UPDATE PROJECT_TABLE SET  WHERE PROJECT_CD = ''";

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
    
    
    
    
    
    
    
    public int delete(ProjectDTO projectDto) {
        String sql = "DELETE FROM PROJECT_TABLE WHERE PROJECT_CD = ''";

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
}
