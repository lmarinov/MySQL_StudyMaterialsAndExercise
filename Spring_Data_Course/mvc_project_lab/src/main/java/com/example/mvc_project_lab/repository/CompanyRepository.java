package com.example.mvc_project_lab.repository;

import com.example.mvc_project_lab.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findFirstByName(String name);

    boolean existsAllBy();
}
