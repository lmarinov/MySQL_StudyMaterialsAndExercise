package com.example.mvc_project_lab.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDto {

    @XmlElement
    private String name;

    @XmlElement
    private String description;

    @XmlElement(name = "start-date")
    private Date startDate;

    @XmlElement(name = "is-finished")
    private boolean isFinished;

    @XmlElement
    private BigDecimal payment;

    @XmlElement
    private CompanyDto company;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public BigDecimal getPayment() {
        return this.payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public CompanyDto getCompany() {
        return this.company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }
}
