package com.rnb.profmng.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rnb.profmng.dto.ProjectDTO;

@Repository
public class ProjectRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(ProjectDTO projectDto) {
        String sql = "INSERT INTO project_table (project_cd, project_nm, start_date, end_date, pm_id, client, contractor, man_month, tot_amt, project_type) " +
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
}
