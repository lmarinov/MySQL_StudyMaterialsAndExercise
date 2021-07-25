package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends User{

    private BigDecimal salary;
    private String email;
    private Set<Course> courses;

    public Teacher() {
    }

    @Column(name = "wage", precision = 19, scale = 2)
    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal wage) {
        this.salary = wage;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy="teacher")
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
