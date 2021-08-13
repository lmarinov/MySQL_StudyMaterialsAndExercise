package com.example.mvc_project_lab.repository;

import com.example.mvc_project_lab.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findFirstByNameAndCompanyName(String name, String company_name);

    List<Project> findAllByFinishedIsTrue();

    boolean existsAllBy();
}
