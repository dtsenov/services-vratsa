package bg.softuni.vikuslugivratsa.model.entity;

import bg.softuni.vikuslugivratsa.model.enums.PipeSizeEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "water_taps")
public class WaterTapEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false)
    private PipeSizeEnum brand;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private PipeSizeEnum size;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    private WorkerEntity worker;

    @ManyToOne
    private ClientEntity client;

    public WaterTapEntity() {
    }

    public PipeSizeEnum getBrand() {
        return brand;
    }

    public void setBrand(PipeSizeEnum brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PipeSizeEnum getSize() {
        return size;
    }

    public void setSize(PipeSizeEnum size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WorkerEntity getWorker() {
        return worker;
    }

    public void setWorker(WorkerEntity worker) {
        this.worker = worker;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
