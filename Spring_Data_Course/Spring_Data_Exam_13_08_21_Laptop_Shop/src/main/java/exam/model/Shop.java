package exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity{

    private String name;
    private BigDecimal income;
    private String address;
    private Integer employeeCount;
    private Integer shopArea;
    private Town town;

    public Shop() {
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public BigDecimal getIncome() {
        return this.income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Column(nullable = false)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "employee_count", nullable = false)
    public Integer getEmployeeCount() {
        return this.employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    @Column(name = "shop_area", nullable = false)
    public Integer getShopArea() {
        return this.shopArea;
    }

    public void setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
    }

    @ManyToOne
    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}