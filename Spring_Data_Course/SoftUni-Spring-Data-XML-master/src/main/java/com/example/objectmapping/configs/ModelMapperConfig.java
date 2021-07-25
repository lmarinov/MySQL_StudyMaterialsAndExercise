package com.example.objectmapping.configs;

import com.example.objectmapping.models.dto.EmployeeDto;
import com.example.objectmapping.models.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapperBasicEmployee() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Employee, EmployeeDto> typeMap = mapper.createTypeMap(Employee.class, EmployeeDto.class);
        typeMap.addMappings(mapping -> mapping.map(Employee::getSalary, EmployeeDto::setIncome));

        return mapper;
    }

    @Bean
    public Date nowDate() {
        return new Date();
    }
}
