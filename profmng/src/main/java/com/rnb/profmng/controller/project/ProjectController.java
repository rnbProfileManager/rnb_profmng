package com.rnb.profmng.controller.project;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnb.profmng.dto.project.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.entity.project.ProjectPK;
import com.rnb.profmng.repository.project.ProjectRepo;
import com.rnb.profmng.service.project.ProjectService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProjectController{

	@Autowired
    private final ProjectService projectService;
    
    @Autowired
    private final ProjectRepo projectRepo;
    
    // 프로젝트 탭
    @GetMapping("/project")
    public String project(Model model) {
        List<ProjectDTO> projectList = projectService.allProjects();
        model.addAttribute("projectList", projectList);
        return "project/project";
    }

    // 특정 프로젝트 조회
    @GetMapping("/project/manage")
    public String selectProject(@RequestParam("projectCd") String projectCd, Model model) {
        List<Project> result = projectRepo.findByProjectPk_ProjectCd(projectCd);
        model.addAttribute("projectList", result);
        return "project/project";
    }
    
    // 신규 프로젝트 추가
    @GetMapping("/project/addProject")
    public String showAddProjectPage() {
        return "project/addProject";
    }
    
    @PostMapping("/project/add")
    public String addProjectPage(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {

        try {
            ProjectPK pk = new ProjectPK(
                    projectDto.getProjectCd(),
                    projectDto.getProjectNm(),
                    projectDto.getStartDate()
                );

            if (projectService.existsByPk(pk)) {
                redirectAttributes.addFlashAttribute("addResult", "duplicate");
                return "redirect:/project/addProject";
            }
        	
            projectService.save(projectDto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/project/addProject";
    }

    // 프로젝트 수정
    @GetMapping("/project/edit")
    public String editProjectForm(
            @RequestParam("projectCd") String projectCd,
            @RequestParam("projectNm") String projectNm,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            Model model) {

    	
        ProjectPK pk = new ProjectPK(projectCd, projectNm, startDate);
        Project project = projectService.findByPk(pk);
        model.addAttribute("projectCd", projectCd);
        model.addAttribute("projectNm", projectNm);
        model.addAttribute("startDate", startDate);
        return "project/editProject";
    }
    
    @PostMapping("/project/edit")
    public String editProjectPage(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {
        try {
            ProjectPK pk = new ProjectPK(
                projectDto.getProjectCd(),
                projectDto.getProjectNm(),
                projectDto.getStartDate()
            );

            Project existingProject = projectService.findByPk(pk);

            projectService.updateProjectFromDto(projectDto, existingProject);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/project/edit?projectCd=" + projectDto.getProjectCd()
             + "&projectNm=" + projectDto.getProjectNm()
             + "&startDate=" + projectDto.getStartDate();
    }
    
    // 프로젝트 삭제
    @GetMapping("/project/delete")
    public String deleteProjectPage(@RequestParam("projectCd") List<String> projectCds, RedirectAttributes redirectAttributes) {
        try {
            for (String projectCd : projectCds) {
                projectService.deleteProject(projectCd);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/project";
    }
}