package bg.softuni.vikuslugivratsa.model.entity;

import bg.softuni.vikuslugivratsa.model.enums.PipeSizeEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private PipeSizeEnum size;

    @Column(name = "brand", nullable = false)
    private String brand;


    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    private UserEntity client;

    public ProductEntity() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PipeSizeEnum getSize() {
        return size;
    }

    public void setSize(PipeSizeEnum size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getClient() {
        return client;
    }

    public void setClient(UserEntity client) {
        this.client = client;
    }
}
