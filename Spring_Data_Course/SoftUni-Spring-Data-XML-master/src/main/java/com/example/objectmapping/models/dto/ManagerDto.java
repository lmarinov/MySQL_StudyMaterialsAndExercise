package com.example.objectmapping.models.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerDto extends BasicEmployeeDto {

    @Expose
    @XmlElementWrapper(name = "subordinates")
    @XmlElement(name = "employees")
    private List<EmployeeDto> subordinates;

    public List<EmployeeDto> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<EmployeeDto> subordinates) {
        this.subordinates = subordinates;
    }
}
