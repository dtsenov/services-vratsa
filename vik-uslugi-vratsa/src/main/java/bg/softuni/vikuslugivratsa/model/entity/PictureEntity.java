package bg.softuni.vikuslugivratsa.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {

    @NotBlank
    private String title;

    @NotBlank
    private String url;

    @NotBlank
    private String publicId;

    @OneToOne(mappedBy = "picture")
    private UserEntity client;

    @OneToOne(mappedBy = "picture")
    private ProductEntity product;

    public PictureEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public UserEntity getClient() {
        return client;
    }

    public void setClient(UserEntity client) {
        this.client = client;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
