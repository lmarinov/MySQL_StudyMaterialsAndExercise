package com.example.objectmapping.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"firstName", "lastName", "income"})
public class EmployeeDto extends BasicEmployeeDto {

    @Expose
    @SerializedName("salary")
    @XmlAttribute
    private BigDecimal income;

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal salary) {
        this.income = salary;
    }
}
