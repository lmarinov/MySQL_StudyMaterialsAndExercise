package com.example.springdatadtoexercise.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String email;
    private String password;
    private String fullName;
    private Set<Game> games;
    private Boolean isAdmin;

    public User() {
    }

    @ManyToMany
    public Set<Game> getGames() {
        return this.games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Column
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "is_admin")
    public Boolean getAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
