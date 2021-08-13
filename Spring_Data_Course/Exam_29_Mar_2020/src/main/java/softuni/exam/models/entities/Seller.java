package softuni.exam.models.entities;

import softuni.exam.models.entities.enums.Rating;

import javax.persistence.*;

@Entity
@Table(name="sellers")
public class Seller extends BaseEntity{

    private String firstName;
    private String lastName;
    private String email;
    private Rating rating;
    private String town;

    public Seller() {
    }

    @Column(length = 19)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(length = 19)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(unique = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Enumerated(value = EnumType.STRING)
    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Column(nullable = false)
    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
