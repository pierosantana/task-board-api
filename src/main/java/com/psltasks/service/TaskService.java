package com.psltasks.service;

import com.psltasks.exception.ResourceNotFoundException;
import com.psltasks.model.Project;
import com.psltasks.model.Task;
import com.psltasks.model.TaskStatus;
import com.psltasks.model.User;
import com.psltasks.repository.ProjectRepository;
import com.psltasks.repository.TaskRepository;
import com.psltasks.repository.UserCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserCrudRepository userRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, UserCrudRepository userRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task create(Task task) {
        hydrateRelations(task);
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }
        return taskRepository.save(task);
    }

    public Task update(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());

        if (updatedTask.getUser() != null) {
            Long userId = updatedTask.getUser().getId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + userId));
            existingTask.setUser(user);
        }

        if (updatedTask.getProject() != null) {
            Long projectId = updatedTask.getProject().getId();
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + projectId));
            existingTask.setProject(project);
        }

        return taskRepository.save(existingTask);
    }

    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));
        taskRepository.delete(task);
    }

    private void hydrateRelations(Task task) {
        if (task.getUser() != null) {
            Long userId = task.getUser().getId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + userId));
            task.setUser(user);
        }

        if (task.getProject() != null) {
            Long projectId = task.getProject().getId();
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + projectId));
            task.setProject(project);
        }
    }
}
