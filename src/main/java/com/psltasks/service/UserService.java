package com.psltasks.service;

import com.psltasks.exception.ResourceNotFoundException;
import com.psltasks.model.User;
import com.psltasks.repository.UserCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserCrudRepository userRepository;

    public UserService(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    public User create(User user) {
        return userRepository.save(user);
    }
}
