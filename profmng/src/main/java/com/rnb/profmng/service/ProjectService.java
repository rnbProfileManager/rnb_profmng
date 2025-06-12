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
    
    public void save(ProjectDTO dto) {
        Project project = new Project();

        // ⚠️ projectPk 객체 먼저 생성 후 값 설정
        ProjectPK pk = new ProjectPK();
        pk.setProjectCd(dto.getProjectCd());
        pk.setProjectNm(dto.getProjectNm());
        pk.setStartDate(dto.getStartDate());

        project.setProjectPk(pk);

        // DTO → Entity 수동 매핑
        project.setEndDate(dto.getEndDate());
        project.setPmId(dto.getPmId());
        project.setClient(dto.getClient());
        project.setContractor(dto.getContractor());
        project.setManMonth(dto.getManMonth());
        project.setTotAmt(dto.getTotAmt());
        project.setProjectType(dto.getProjectType());

        projectRepo.save(project);  // INSERT
    }
    
    public List<ProjectDTO> allProjects() {
        return projectRepo.findAll().stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }
    
    public int deleteProject(String projectCd) {
        return projectRepo.deleteByProjectCd(projectCd);
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






