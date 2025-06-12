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

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.entity.project.Project;
import com.rnb.profmng.entity.project.ProjectPK;
import com.rnb.profmng.repository.ProjectRepo;
import com.rnb.profmng.service.ProjectService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProjectController{

    private final ProjectService projectService;
    
    @Autowired
    private final ProjectRepo projectRepo;
    
    // API용 전체조회
    @GetMapping("/api/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.allProjects();
    }
    
    @GetMapping("/project/addProject")
    public String showAddProjectPage() {
        return "project/addProject";
    }
    
    @PostMapping("/project/add")
    public String addProjectPage(@ModelAttribute ProjectDTO projectDto, RedirectAttributes redirectAttributes) {

        try {
            projectService.save(projectDto);
            redirectAttributes.addFlashAttribute("addResult", "success");
        } catch (Exception e) {
        	System.out.println(e);
        	redirectAttributes.addFlashAttribute("addResult", "exception");
        }
        return "redirect:/project/addProject";
    }

//    // API용 검색
//    @GetMapping("/api/projects/search")
//    public List<ProjectDTO> searchProjects(
//            @RequestParam(required = false) String empNm,
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate) {
//        return projectService.searchProjects(empNm, startDate, endDate);
//    }
    
    @GetMapping("/project")
    public String project(Model model) {
        List<ProjectDTO> projectList = projectService.allProjects();
        model.addAttribute("projectList", projectList);
        return "project/project";
    }

//    // 화면 진입 + 검색
//    @GetMapping("/project/manage")
//    public String manage(
//            @RequestParam(required = false) String empNm,
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate,
//            Model model) {
//
//        List<ProjectDTO> employeeList = projectService.searchProjects(empNm, startDate, endDate);
//        model.addAttribute("employeeList", employeeList);
//        return "web/project";
//    }
    
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
            // 복합키 생성
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

        // PRG 패턴: 새로고침 시 중복방지
        return "redirect:/project/edit?projectCd=" + projectDto.getProjectCd()
             + "&projectNm=" + projectDto.getProjectNm()
             + "&startDate=" + projectDto.getStartDate();
    }
    
    @GetMapping("/project/delete")
    public String deleteProjectPage(@RequestParam("projectCd") List<String> projectCds, RedirectAttributes redirectAttributes) {
        try {
            for (String projectCd : projectCds) {
                projectService.deleteProject(projectCd);  // 서비스에서 개별 삭제 수행
            }
            redirectAttributes.addFlashAttribute("deleteResult", "success");
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("deleteResult", "exception");
        }
        return "redirect:/project";
    }
}