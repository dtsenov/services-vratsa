package bg.softuni.vikuslugivratsa.model.service;

import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ServicesServiceModel {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private MultipartFile picture;

    public ServicesServiceModel() {
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
