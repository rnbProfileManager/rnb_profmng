package com.rnb.profmng.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.entity.project.ProjectPK;
import com.rnb.profmng.repository.ProjectRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
    @Autowired
    private ProjectRepo projectRepo;
    
    @Autowired
    private final ProjectMapper projectMapper;  
    
    public Project findByCode(String projectCd) {
        return projectRepo.findByProjectPk_ProjectCd(projectCd)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + projectCd));
    }
    
    public Project insertProject(ProjectDTO dto) {
    	
        ProjectPK id = new ProjectPK(dto.getProjectCd(), dto.getProjectNm(), dto.getStartDate());

        if (projectRepo.existsById(id)) {
            throw new DuplicateKeyException("Duplicate");
        }
    	
        ProjectPK projectPk = new ProjectPK();
        projectPk.setProjectCd(dto.getProjectCd());
        projectPk.setProjectNm(dto.getProjectNm());
        projectPk.setStartDate(dto.getStartDate());
        
        Project project = new Project();
        project.setProjectPk(projectPk);
        
        project.setEndDate(dto.getEndDate());
        project.setUpdateDate(dto.getUpdateDate());
        project.setPmId(dto.getPmId());
        project.setClient(dto.getClient());
        project.setContractor(dto.getContractor());
        project.setManMonth(dto.getManMonth());
        project.setTotAmt(dto.getTotAmt());
        project.setProjectType(dto.getProjectType());

        return projectRepo.save(project);
    }
    
    public List<ProjectDTO> selectProject(String projectCd) {
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
    
    public List<ProjectDTO> allProjects() {
    	
        return projectRepo.findAll().stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public boolean updateByProjectCdOnly(ProjectDTO dto) {
        Optional<Project> optional = projectRepo.findByProjectPk_ProjectCd(dto.getProjectCd());
        System.out.println("updateByProjectCdOnly");
        System.out.println(optional);

        if (optional.isPresent()) {
            Project project = optional.get();

            // ⚠️ PK 변경은 하지 말고, 나머지 값만 덮어쓰기
            project.getProjectPk().setProjectNm(dto.getProjectNm());
            project.getProjectPk().setStartDate(dto.getStartDate());
            project.setEndDate(dto.getEndDate());
            project.setPmId(dto.getPmId());
            project.setClient(dto.getClient());
            project.setContractor(dto.getContractor());
            project.setManMonth(dto.getManMonth());
            project.setTotAmt(dto.getTotAmt());
            project.setProjectType(dto.getProjectType());

            System.out.println("updateByProjectCdOnly");
            System.out.println(project);
            // 저장 (dirty checking)
            projectRepo.save(project);
            return true;
        }

        // A에 해당하는 레코드 자체가 없다면 실패
        return false;
    }
    
    public int deleteProject(String projectCd) {
    	
        return projectRepo.deleteByProjectCd(projectCd);
    }
    
    @Transactional
    public boolean  updateProject(String projectCd, ProjectDTO dto) {
        ProjectPK pk = new ProjectPK();
        pk.setProjectCd(dto.getProjectCd());
        pk.setProjectNm(dto.getProjectNm());
        pk.setStartDate(dto.getStartDate());
        
        Project project = projectRepo.findById(pk)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

        projectMapper.updateProjectFromDto(dto, project);
        
        return true;
    }
    
    @Transactional
    public boolean updateProjectByCdOnly(String projectCd, ProjectDTO dto) {
        Optional<Project> optional = projectRepo.findByProjectPk_ProjectCd(projectCd);

        if (optional.isPresent()) {
            Project project = optional.get();

            // PK는 건드리지 않고 나머지 필드만 MapStruct로 업데이트
            projectMapper.updateProjectFromDto(dto, project);
            return true;
        }

        return false;
    }
    
    public Project findByPk(ProjectPK pk) {
        return projectRepo.findById(pk)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    
    @Transactional
    public void updateProjectFromDto(ProjectDTO dto, Project entity) {
        // 필드 null 체크해서 업데이트
        entity.setClient(dto.getClient());
        entity.setContractor(dto.getContractor());
        entity.setEndDate(dto.getEndDate());
        entity.setManMonth(dto.getManMonth());
        entity.setPmId(dto.getPmId());
        entity.setProjectType(dto.getProjectType());
        entity.setTotAmt(dto.getTotAmt());
        entity.setUpdateDate(LocalDate.now());
        // 저장은 안 해도 @Transactional 로 커밋됨
    }
}






