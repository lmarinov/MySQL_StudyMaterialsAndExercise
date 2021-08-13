package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity{

    private Float shooting;
    private Float passing;
    private Float endurance;

    public Stat() {
    }

    @Column(nullable = false)
    public Float getShooting() {
        return this.shooting;
    }

    public void setShooting(Float shooting) {
        this.shooting = shooting;
    }

    @Column(nullable = false)
    public Float getPassing() {
        return this.passing;
    }

    public void setPassing(Float passing) {
        this.passing = passing;
    }

    @Column(nullable = true)
    public Float getEndurance() {
        return this.endurance;
    }

    public void setEndurance(Float endurance) {
        this.endurance = endurance;
    }
}
