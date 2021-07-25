package com.example.springdatadtolab.services;

import com.example.springdatadtolab.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto findOne(Long id);
}
