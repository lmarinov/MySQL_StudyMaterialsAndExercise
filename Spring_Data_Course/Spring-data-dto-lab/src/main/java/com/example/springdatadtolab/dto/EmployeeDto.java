package com.example.springdatadtolab.dto;

import com.example.springdatadtolab.entities.Employee;

import java.math.BigDecimal;

public class EmployeeDto {

    private String firstName;
    private String lastName;
    private BigDecimal salary;

    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public static EmployeeDto ofEmployee(Employee employee) {
        return new EmployeeDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary()
        );
    }

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

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
