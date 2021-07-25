package springdata.springdatalab.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User extends BaseEntity{

    @Column(unique = true)
    private String username;

    private int age;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
