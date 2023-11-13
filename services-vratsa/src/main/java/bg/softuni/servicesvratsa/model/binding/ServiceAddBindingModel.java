package bg.softuni.servicesvratsa.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ServiceAddBindingModel {

    @NotBlank(message = "Не трябва да е празно!")
    private String name;

    @NotBlank(message = "Описанието не трябва да е празно!")
    @Length(min = 5, message = "Дължината трябва да е над 5 символа.")
    private String description;

    @Positive(message = "Цената трябва да е положителна!")
    @NotNull(message = "Не трябва да е празно!")
    private BigDecimal price;

    @NotNull(message = "Снимката на продукта е задължителна!")
    private MultipartFile picture;

    public ServiceAddBindingModel() {
        price = BigDecimal.ZERO;
        picture = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
