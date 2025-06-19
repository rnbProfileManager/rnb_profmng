package com.rnb.profmng.controller.project;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    public String selectProject(@RequestParam("pjtSeq") long pjtSeq, Model model) {
        List<Project> result = projectRepo.findByProjectPk_PjtSeq(pjtSeq);
        model.addAttribute("projectList", result);
        return "project/project";
    }
    
    // 신규 프로젝트 추가
    @GetMapping("/project/addPjtBas")
    public String showAddProjectPage() {
        return "project/addPjtBas";
    }
    
    @PostMapping("/project/addPjtBas")
    public String addProjectPage(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {

        try {
            ProjectPK pk = new ProjectPK(
                    projectDto.getPjtSeq(),
                    projectDto.getPjtNm(),
                    projectDto.getEfctStartDate()
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
        return "redirect:/project/addPjtBas";
    }

    // 프로젝트 수정
    @GetMapping("/project/editPjtBas")
    public String editProjectForm(
            @RequestParam("pjtSeq") long pjtSeq,
            @RequestParam("pjtNm") String pjtNm,
            @RequestParam("efctStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate efctStartDate,
            Model model) {

    	
        ProjectPK pk = new ProjectPK(pjtSeq, pjtNm, efctStartDate);
        Project project = projectService.findByPk(pk);
        model.addAttribute("pjtSeq", pjtSeq);
        model.addAttribute("pjtNm", pjtNm);
        model.addAttribute("efctStartDate", efctStartDate);
        return "project/editPjtBas";
    }
    
    @PostMapping("/project/editPjtBas")
    public String editProjectPage(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {
        try {
            ProjectPK pk = new ProjectPK(
                projectDto.getPjtSeq(),
                projectDto.getPjtNm(),
                projectDto.getEfctStartDate()
            );

            Project existingProject = projectService.findByPk(pk);

            projectService.updateProjectFromDto(projectDto, existingProject);
            redirectAttributes.addFlashAttribute("editResult", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editResult", "exception");
        }

        return "redirect:/project/editPjtBas?pjtSeq=" + projectDto.getPjtSeq()
             + "&pjtNm=" + URLEncoder.encode(projectDto.getPjtNm(), StandardCharsets.UTF_8)
             + "&efctStartDate=" + projectDto.getEfctStartDate();
    }
    
    // 프로젝트 삭제
    @GetMapping("/project/deletePjtBas")
    public String deleteProjectPage(@RequestParam("pjtSeq") List<String> pjtSeqs, RedirectAttributes redirectAttributes) {
        try {
            for (String pjtSeq : pjtSeqs) {
                projectService.deleteProject(pjtSeq);
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/project";
    }
}