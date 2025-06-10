package com.rnb.profmng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.repository.ProjectRepo;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    /*
     * 대시보드
     * select Repo 호출
     */
    public List selectProject() {
    	List result = projectRepo.select();
    	
    	return result;
    }
    
    /*
     * 프로젝트 관리
     * select, insert, update, delete Repo 호출
     */
    public List selectProject(String projectCd) {
    	List result = projectRepo.select(projectCd);
    	
    	return result;
    }
    
    public List selectAll() {
    	List result = projectRepo.selectAll();
    	
    	return result;
    }
    
    public int insertProject(ProjectDTO projectDto) {
    	int result = projectRepo.insert(projectDto);
    	
    	return result;
    }
    
    public int updateProject(ProjectDTO projectDto) {
    	int result = projectRepo.update(projectDto);
    	
    	return result;

    }
    
    public int deleteProject(String projectCd) {
    	int result = projectRepo.delete(projectCd);
    	
    	return result;
    }
}






