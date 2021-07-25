package com.example.springdatadtoexercise.repository;

import com.example.springdatadtoexercise.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findEmailAndFullName(String email, String password);
}
