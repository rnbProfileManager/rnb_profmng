package com.rnb.profmng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.repository.ProjectRepo;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    public int insertProject(ProjectDTO projectDto) {
    	int result = projectRepo.insert(projectDto);
    	
    	return result;
    }
}