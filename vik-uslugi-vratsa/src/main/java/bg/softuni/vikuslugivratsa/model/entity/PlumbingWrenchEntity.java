package bg.softuni.vikuslugivratsa.model.entity;

import bg.softuni.vikuslugivratsa.model.enums.PlumbingWrenchBrandEnum;
import bg.softuni.vikuslugivratsa.model.enums.PlumbingWrenchSizeEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "plumbing_wrenches")
public class PlumbingWrenchEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false)
    private PlumbingWrenchBrandEnum brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private PlumbingWrenchSizeEnum size;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    private ClientEntity client;

    public PlumbingWrenchEntity() {
    }

    public PlumbingWrenchBrandEnum getBrand() {
        return brand;
    }

    public void setBrand(PlumbingWrenchBrandEnum brand) {
        this.brand = brand;
    }

    public PlumbingWrenchSizeEnum getSize() {
        return size;
    }

    public void setSize(PlumbingWrenchSizeEnum size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
