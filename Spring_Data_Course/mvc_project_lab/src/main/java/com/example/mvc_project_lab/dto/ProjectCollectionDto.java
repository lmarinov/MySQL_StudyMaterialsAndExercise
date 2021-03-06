package com.example.mvc_project_lab.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectCollectionDto {

    @XmlElement(name = "project")
    private List<ProjectDto> projects;

    public List<ProjectDto> getProjects() {
        return this.projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }
}
