package bg.softuni.vikuslugivratsa.model.entity;

import bg.softuni.vikuslugivratsa.model.enums.PipeSizeEnum;
import bg.softuni.vikuslugivratsa.model.enums.ProductTypeEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProductTypeEnum type;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    private UserEntity client;

    @OneToOne(fetch = FetchType.EAGER)
    private PictureEntity picture;

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

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
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

    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }
}
