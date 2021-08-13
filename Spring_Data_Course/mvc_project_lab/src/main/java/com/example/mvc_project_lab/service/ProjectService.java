package com.example.mvc_project_lab.service;

import com.example.mvc_project_lab.dto.ExportedProjectDto;
import com.example.mvc_project_lab.dto.ProjectDto;
import com.example.mvc_project_lab.entity.Project;

import java.io.IOException;
import java.util.List;

public interface ProjectService {

    String FILE_PATH = "files/xmls/projects.xml";

    boolean exists();

    String getXmlForImport() throws IOException;

    Long create(ProjectDto request);

    Project find(Long id);

    List<ExportedProjectDto> finishedProjects();
}
