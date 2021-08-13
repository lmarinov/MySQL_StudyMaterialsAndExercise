package com.example.football.models.dto;

import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.models.enumeration.Position;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerCreateDto {

    @XmlElement(name = "first-name")
    @Expose
    private String firstName;
    @XmlElement(name = "last-name")
    @Expose
    private String lastName;
    @XmlElement(name = "email")
    @Expose
    private String email;
    @XmlElement(name = "birth-date")
    @Expose
    private LocalDate birthDate;
    @XmlElement(name = "position")
    @Expose
    private Position position;
    @XmlElement(name = "town")
    @Expose
    private Town town;
    @XmlElement(name = "team")
    @Expose
    private Team team;
    @XmlElement(name = "stat")
    @Expose
    private Stat stat;

    public PlayerCreateDto() {
    }


    @Size(min = 2)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 2)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email(message = "Invalid player")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^(\\d){2}/(\\d){2}/(\\d){4}$")
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Stat getStat() {
        return this.stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
