package com.example.mvc_project_lab.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyCollectionDto {

    @XmlElement(name = "company")
    private List<CompanyDto> companies;

    public List<CompanyDto> getCompanies() {
        return this.companies;
    }

    public void setCompanies(List<CompanyDto> companies) {
        this.companies = companies;
    }
}
