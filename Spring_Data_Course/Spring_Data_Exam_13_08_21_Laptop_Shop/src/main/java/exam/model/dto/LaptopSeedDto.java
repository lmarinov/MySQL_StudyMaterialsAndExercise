package exam.model.dto;

import com.google.gson.annotations.Expose;
import exam.model.Shop;
import exam.model.enums.WarrantyType;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class LaptopSeedDto {

    @Expose
    private String macAddress;
    @Expose
    private Double cpuSpeed;
    @Expose
    private Integer ram;
    @Expose
    private Integer storage;
    @Expose
    private String description;
    @Expose
    private BigDecimal price;
    @Expose
    private WarrantyType warrantyType;
    @Expose
    private ShopNameDto shop;

    public LaptopSeedDto() {
    }

    @Size(min = 9)
    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Double getCpuSpeed() {
        return this.cpuSpeed;
    }

    public void setCpuSpeed(Double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    @Min(value = 8)
    @Max(value = 128)
    public Integer getRam() {
        return this.ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    @Min(value = 128)
    @Max(value = 1024)
    public Integer getStorage() {
        return this.storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    @Size(min = 10)
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

    @NotNull
    public WarrantyType getWarrantyType() {
        return this.warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public ShopNameDto getShop() {
        return this.shop;
    }

    public void setShop(ShopNameDto shop) {
        this.shop = shop;
    }
}
