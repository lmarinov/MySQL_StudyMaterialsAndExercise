package entities;

import customerOrmFramework.annotations.Column;
import customerOrmFramework.annotations.Entity;

import java.time.LocalDate;

@Entity(name = "users")
public class User extends BaseEntity{

    @Column(name = "usersname")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private Integer age;
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public User() {
    }

    public User(String username, String password, Integer age, LocalDate registrationDate) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.registrationDate = registrationDate;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public LocalDate getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
