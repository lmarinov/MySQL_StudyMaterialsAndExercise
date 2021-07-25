package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation extends BaseEntity{

    private String location_name;
    private Set<Sale> sales;

    public StoreLocation() {
        this.sales = new HashSet<>();
    }

    @Column(name = "location_name", unique = true)
    public String getLocation_name() {
        return this.location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    @OneToMany(mappedBy = "storeLocation")
    public Set<Sale> getSales() {
        return this.sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
