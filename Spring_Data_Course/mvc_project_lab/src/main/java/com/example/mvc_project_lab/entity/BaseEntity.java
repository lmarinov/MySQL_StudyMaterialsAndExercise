package com.example.mvc_project_lab.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {


    private Long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
