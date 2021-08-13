package softuni.exam.models.dtos;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {

    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "added-on")
    private String addedOn;
    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;
    @XmlElement(name = "car")
    private CarIdDto car;
    @XmlElement(name = "seller")
    private SellerIdDto seller;

    public OfferSeedDto() {
    }

    @Size(min = 5)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Positive
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddedOn() {
        return this.addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public boolean getHasGoldStatus() {
        return this.hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarIdDto getCar() {
        return this.car;
    }

    public void setCar(CarIdDto car) {
        this.car = car;
    }

    public SellerIdDto getSeller() {
        return this.seller;
    }

    public void setSeller(SellerIdDto seller) {
        this.seller = seller;
    }
}
