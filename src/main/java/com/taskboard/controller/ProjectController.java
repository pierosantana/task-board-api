package com.taskboard.controller;

import com.taskboard.model.Project;
import com.taskboard.model.Task;
import com.taskboard.service.ProjectService;
import com.taskboard.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
 

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
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

    @PostMapping("/{projectId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTaskForProject(@PathVariable Long projectId, @RequestBody Task task) {
        return taskService.createForProject(projectId, task);
    }

    @PutMapping("/{projectId}/owner/{ownerId}")
    public Project assignOwner(@PathVariable Long projectId, @PathVariable Long ownerId) {
        return projectService.assignOwner(projectId, ownerId);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getProjectTasks(@PathVariable Long id) {
        return projectService.findById(id).getTasks();
    }

}
