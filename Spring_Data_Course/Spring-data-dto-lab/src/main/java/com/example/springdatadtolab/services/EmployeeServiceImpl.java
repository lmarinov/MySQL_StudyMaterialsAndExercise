package com.example.springdatadtolab.services;

import com.example.springdatadtolab.dto.EmployeeDto;
import com.example.springdatadtolab.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeDto findOne(Long id) {
//        var employee = this.employeeRepository.findById(id).orElseThrow();
//
//        return EmployeeDto.ofEmployee(employee);

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this.employeeRepository.findById(id).orElseThrow(), EmployeeDto.class);
    }
}
