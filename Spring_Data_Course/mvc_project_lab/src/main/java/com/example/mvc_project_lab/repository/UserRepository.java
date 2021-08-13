package com.example.mvc_project_lab.repository;

import com.example.mvc_project_lab.dto.UserRegisterDto;
import com.example.mvc_project_lab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);
}



