package com.example.springdatajsonprocessing.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    private String id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_generator")
    @GenericGenerator(name = "id_generator", strategy = "uuid")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
