package exam.model.dto;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShopSeedDto {

    @XmlElement
    private String address;
    @XmlElement(name = "employee-count")
    private Integer employeeCount;
    @XmlElement
    private BigDecimal income;
    @XmlElement
    private String name;
    @XmlElement(name = "shop-area")
    private Integer shopArea;
    @XmlElement(name = "town")
    private TownNameDto town;

    public ShopSeedDto() {
    }

    @Size(min = 4)
    @NotBlank
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Min(value = 1)
    @Max(value = 50)
    public Integer getEmployeeCount() {
        return this.employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    @Min(value = 20000)
    public BigDecimal getIncome() {
        return this.income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Size(min = 4)
    @NotBlank
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 150)
    public Integer getShopArea() {
        return this.shopArea;
    }

    public void setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
    }

    public TownNameDto getTown() {
        return this.town;
    }

    public void setTown(TownNameDto town) {
        this.town = town;
    }
}
