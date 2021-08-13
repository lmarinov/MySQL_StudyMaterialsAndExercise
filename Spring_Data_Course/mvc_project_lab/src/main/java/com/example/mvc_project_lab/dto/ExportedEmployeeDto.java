package com.example.mvc_project_lab.dto;

public class ExportedEmployeeDto {

    private String firstName;

    private String lastName;

    private int age;

    private String projectName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Name: " + this.getFirstName() + " " + this.getLastName() + "\n"
                + "\tAge: " + this.getAge() + "\n"
                + "\tProject name: " + this.getProjectName();
    }
}
