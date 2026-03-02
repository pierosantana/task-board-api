package com.taskboard.service;

import com.taskboard.exception.ResourceNotFoundException;
import com.taskboard.model.Project;
import com.taskboard.model.User;
import com.taskboard.repository.ProjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ProjectService(ProjectRepository projectRepository, UserService userRepository) {
        this.projectRepository = projectRepository;
        this.userService = userRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + id));
    }

    public Project create(Project project) {
        hydrateOwner(project);
        return projectRepository.save(project);
    }

    public Project createForUser(Long userId, Project project) {
        User owner = userService.findById(userId);
        project.setOwner(owner);
        return projectRepository.save(project);
    }

    public Project assignOwner(Long projectId, Long ownerId) {
        Project project = findById(projectId);
        User owner = userService.findById(ownerId);
        project.setOwner(owner);
        return projectRepository.save(project);
    }

    private void hydrateOwner(Project project) {
        if (project.getOwner() != null && project.getOwner().getId() != null) {
            Long ownerId = project.getOwner().getId();
            User owner = userService.findById(ownerId);
            project.setOwner(owner);
        }
    }
}
