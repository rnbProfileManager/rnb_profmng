package com.rnb.profmng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.repository.ProjectRepo;

@Service
public class DashboardService {
    @Autowired
    private ProjectRepo projectRepo;

    public List selectProject(String projectCd) {
    	List result = projectRepo.findAll();

    	return result;
    }
}






