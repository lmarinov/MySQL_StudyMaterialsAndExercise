package com.example.mvc_project_lab.service;

import com.example.mvc_project_lab.dto.EmployeeDto;
import com.example.mvc_project_lab.dto.ExportedEmployeeDto;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    String FILE_PATH = "files/xmls/employees.xml";

    boolean exists();

    String getXmlForImport() throws IOException;

    Long create(EmployeeDto request);

    List<ExportedEmployeeDto> getEmployeesAfter25();
}
