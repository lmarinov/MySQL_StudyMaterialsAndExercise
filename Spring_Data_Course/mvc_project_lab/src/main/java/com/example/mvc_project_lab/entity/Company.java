package com.example.mvc_project_lab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

    private String name;

    public Company() {
    }

    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
