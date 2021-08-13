package com.example.mvc_project_lab.service;

import com.example.mvc_project_lab.dto.CompanyDto;
import com.example.mvc_project_lab.entity.Company;

import java.io.IOException;

public interface CompanyService {

    String FILE_PATH = "files/xmls/companies.xml";

    boolean exists();

    String getXmlForImport() throws IOException;

    Long create(CompanyDto request);

    Company find(Long id);
}
