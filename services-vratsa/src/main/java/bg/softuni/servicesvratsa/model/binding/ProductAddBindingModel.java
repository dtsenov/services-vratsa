package bg.softuni.servicesvratsa.model.binding;

import bg.softuni.servicesvratsa.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ProductAddBindingModel {

    @NotBlank(message = "Името не трябва да е празно!")
    private String name;

    @Positive(message = "Цената трябва да е положителна!")
    @NotNull(message = "Цената не трябва да е празна!")
    private BigDecimal price;

    @NotBlank(message = "Полето не трябва да е празно!")
    private String brand;

    private ProductTypeEnum type;

    @NotBlank(message = "Описанието не трябва да е празно!")
    @Length(min = 5, message = "Дължината трябва да е над 5 символа.")
    private String description;

    @NotNull(message = "Снимката на продукта е задължителна!")
    private MultipartFile picture;

    public ProductAddBindingModel() {
        picture = null;
        price = BigDecimal.ZERO;
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

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
