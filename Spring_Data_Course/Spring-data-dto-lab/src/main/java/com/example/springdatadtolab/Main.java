package com.example.springdatadtolab;

import com.example.springdatadtolab.dto.EmployeeDto;
import com.example.springdatadtolab.services.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private final EmployeeService employeeService;

    public Main(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        EmployeeDto dto = this.employeeService.findOne(1L);

        System.out.println(dto.getFirstName() + " " + dto.getLastName() + ": " + dto.getSalary() );
    }
}
