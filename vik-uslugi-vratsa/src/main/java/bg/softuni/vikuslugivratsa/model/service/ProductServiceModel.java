package bg.softuni.vikuslugivratsa.model.service;

import bg.softuni.vikuslugivratsa.model.enums.ProductTypeEnum;

import java.math.BigDecimal;

public class ProductServiceModel {

    private Long id;

    private String name;

    private BigDecimal price;

    private String brand;

    private ProductTypeEnum type;

    private String description;

    public ProductServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}
