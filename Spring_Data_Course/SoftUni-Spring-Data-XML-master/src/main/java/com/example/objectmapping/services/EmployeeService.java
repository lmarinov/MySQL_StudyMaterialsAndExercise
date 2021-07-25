package com.example.objectmapping.services;

import com.example.objectmapping.models.dto.EmployeeCreateRequest;
import com.example.objectmapping.models.dto.EmployeeCreateResponse;
import com.example.objectmapping.models.dto.EmployeeDto;
import com.example.objectmapping.models.dto.ManagerDto;

import java.util.List;

public interface EmployeeService {

    ManagerDto findOne(Long id);

    List<ManagerDto> findAll();

    EmployeeCreateResponse save(EmployeeCreateRequest createRequest);

}
