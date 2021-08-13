package com.example.mvc_project_lab.repository;

import com.example.mvc_project_lab.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findFirstByFirstNameAndLastNameAndAge(String firstName, String lastName, Integer age);

    List<Employee> findAllByAgeAfter(int age);

    boolean existsAllBy();
}
