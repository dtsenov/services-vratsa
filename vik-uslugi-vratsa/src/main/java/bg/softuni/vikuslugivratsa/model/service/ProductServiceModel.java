package bg.softuni.vikuslugivratsa.model.service;

import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import bg.softuni.vikuslugivratsa.model.entity.UserEntity;
import bg.softuni.vikuslugivratsa.model.enums.PipeSizeEnum;
import bg.softuni.vikuslugivratsa.model.enums.ProductTypeEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

public class ProductServiceModel {

    private Long id;

    private BigDecimal price;

    private PipeSizeEnum size;

    private String brand;

    private ProductTypeEnum type;

    private String description;

    private PictureEntity picture;
}
