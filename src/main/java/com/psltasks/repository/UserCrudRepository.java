package com.psltasks.repository;


import com.psltasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCrudRepository extends JpaRepository<User, Long> {
}
