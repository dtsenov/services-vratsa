package bg.softuni.servicesvratsa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {

    @OneToMany
    private Set<ProductEntity> products;

    @OneToMany
    private Set<ServiceEntity> services;

    public CartEntity() {
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

    public Set<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(Set<ServiceEntity> services) {
        this.services = services;
    }
}
