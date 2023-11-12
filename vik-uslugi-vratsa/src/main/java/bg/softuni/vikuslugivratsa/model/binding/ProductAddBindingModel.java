package bg.softuni.vikuslugivratsa.model.binding;

import bg.softuni.vikuslugivratsa.model.enums.PipeSizeEnum;
import bg.softuni.vikuslugivratsa.model.enums.ProductTypeEnum;

import java.math.BigDecimal;

public class ProductAddBindingModel {

    private BigDecimal price;
    private PipeSizeEnum size;
    private String brand;
    private ProductTypeEnum type;

    public ProductAddBindingModel() {
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
}
