package com.taskboard.controller;

import com.taskboard.model.Project;
import com.taskboard.model.User;
import com.taskboard.service.UserService;
import com.taskboard.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
 

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;

    public UserController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/{userId}/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProjectForUser(@PathVariable Long userId, @RequestBody Project project) {
        return projectService.createForUser(userId, project);
    }

    @GetMapping("/{userId}/projects")
    public List<Project> getProjectsByUserId(@PathVariable Long userId) {
        return userService.findById(userId).getProjects();
    }
}
