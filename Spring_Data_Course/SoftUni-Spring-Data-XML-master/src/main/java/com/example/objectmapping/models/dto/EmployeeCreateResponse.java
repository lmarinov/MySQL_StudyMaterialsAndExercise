package com.example.objectmapping.models.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class EmployeeCreateResponse extends EmployeeCreateRequest {

    @Expose
    @XmlElement
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
