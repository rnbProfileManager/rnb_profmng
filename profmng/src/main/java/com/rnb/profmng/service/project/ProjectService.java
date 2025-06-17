package com.rnb.profmng.service.project;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rnb.profmng.dto.project.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.entity.project.ProjectPK;
import com.rnb.profmng.repository.project.ProjectRepo;
import com.rnb.profmng.service.NullAwareBeanUtilsBean;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
    @Autowired
    private ProjectRepo projectRepo;
    
    // 신규 프로젝트 추가
    public void save(ProjectDTO dto) {
        Project project = new Project();

        ProjectPK pk = new ProjectPK();
        pk.setProjectCd(dto.getProjectCd());
        pk.setProjectNm(dto.getProjectNm());
        pk.setStartDate(dto.getStartDate());

        project.setProjectPk(pk);

        project.setEndDate(dto.getEndDate());
        project.setPmId(dto.getPmId());
        project.setClient(dto.getClient());
        project.setContractor(dto.getContractor());
        project.setManMonth(dto.getManMonth());
        project.setTotAmt(dto.getTotAmt());
        project.setProjectType(dto.getProjectType());

        projectRepo.save(project);
    }
    
    // 모든 프로젝트 조회
    public List<ProjectDTO> allProjects() {
        return projectRepo.findAll().stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }
    
    // 프로젝트 삭제
    public int deleteProject(String projectCd) {
        return projectRepo.deleteByProjectCd(projectCd);
    }
    
    // 프로젝트 수정 - PK 체크    
    public Project findByPk(ProjectPK pk) {
        return projectRepo.findById(pk)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    
    // 프로젝트 추가 - PK 체크
    public boolean existsByPk(ProjectPK pk) {
        return projectRepo.existsById(pk);
    }
    
    // 프로젝트 수정
    @Transactional
    public void updateProjectFromDto(ProjectDTO dto, Project entity) {
        try {
            new NullAwareBeanUtilsBean().copyProperties(entity, dto);
            //entity.setUpdateDate(LocalDate.now());
        } catch (Exception e) {
            throw new RuntimeException("프로퍼티 복사 실패", e);
        }
    }
}






