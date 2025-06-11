package com.rnb.profmng.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.entity.project.ProjectPK;
import com.rnb.profmng.repository.ProjectRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService {
	
    @Autowired
    private ProjectRepo projectRepo;

    public Project insert(ProjectDTO projectDto) {
    	
        ProjectPK id = new ProjectPK(projectDto.getProjectCd(), projectDto.getProjectNm(), projectDto.getStartDate());

        if (projectRepo.existsById(id)) {
            throw new DuplicateKeyException("Duplicate");
        }
    	
        ProjectPK projectPk = new ProjectPK();
        projectPk.setProjectCd(projectDto.getProjectCd());
        projectPk.setProjectNm(projectDto.getProjectNm());
        projectPk.setStartDate(projectDto.getStartDate());
        
        Project project = new Project();
        project.setProjectPk(projectPk);
        
        project.setEndDate(projectDto.getEndDate());
        project.setUpdateDate(projectDto.getUpdateDate());
        project.setPmId(projectDto.getPmId());
        project.setClient(projectDto.getClient());
        project.setContractor(projectDto.getContractor());
        project.setManMonth(projectDto.getManMonth());
        project.setTotAmt(projectDto.getTotAmt());
        project.setProjectType(projectDto.getProjectType());

        return projectRepo.save(project);
    }
    
    public List<ProjectDTO> select(String projectCd) {
        List<Project> projects = projectRepo.findByProjectPk_ProjectCdContaining(projectCd);

		return projects.stream()
				.map(project -> new ProjectDTO(
						project.getProjectPk().getProjectCd(),
						project.getProjectPk().getProjectNm(),
						project.getProjectPk().getStartDate(),
						project.getEndDate(),
						project.getUpdateDate(),
						project.getPmId(),
						project.getClient(),
						project.getContractor(),
						project.getManMonth(),
						project.getTotAmt(),
						project.getProjectType()))
                       .collect(Collectors.toList());
    }
    
    public List<ProjectDTO> selectAll() {
    	
        return projectRepo.findAll().stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }
    
    public Project update(ProjectDTO dto) {
    	
    	Project existing = projectRepo.findByProjectPk_ProjectCd(dto.getProjectCd())
    	        .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    	
        NullIgnoringBeanUtils.copyNonNullProperties(dto, existing);
        
        existing.setUpdateDate(LocalDate.now());
        
        return projectRepo.save(existing);
    }
}






