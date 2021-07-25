package com.example.springdatajsonprocessingexercise.model.dto;

import com.google.gson.annotations.Expose;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

public class UserSeedDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;

    public UserSeedDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min= 3)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
