package bg.softuni.vikuslugivratsa.model.entity;

import bg.softuni.vikuslugivratsa.model.enums.PipeSizeEnum;
import bg.softuni.vikuslugivratsa.model.enums.WaterMeterBrandEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "water_meters")
public class WaterMeterEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false)
    private WaterMeterBrandEnum brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private PipeSizeEnum size;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    private RoleEntity worker;

    @ManyToOne
    private UserEntity client;

    public WaterMeterEntity() {
    }

    public WaterMeterBrandEnum getBrand() {
        return brand;
    }

    public void setBrand(WaterMeterBrandEnum brand) {
        this.brand = brand;
    }

    public PipeSizeEnum getSize() {
        return size;
    }

    public void setSize(PipeSizeEnum size) {
        this.size = size;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RoleEntity getWorker() {
        return worker;
    }

    public void setWorker(RoleEntity worker) {
        this.worker = worker;
    }

    public UserEntity getClient() {
        return client;
    }

    public void setClient(UserEntity client) {
        this.client = client;
    }
}
