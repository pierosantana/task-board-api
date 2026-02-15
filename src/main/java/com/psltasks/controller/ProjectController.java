package com.psltasks.controller;

import com.psltasks.model.Project;
import com.psltasks.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) {
        return projectService.create(project);
    }
}
