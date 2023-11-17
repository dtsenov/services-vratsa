package bg.softuni.servicesvratsa.model.view;

import bg.softuni.servicesvratsa.model.entity.ProductEntity;
import bg.softuni.servicesvratsa.model.entity.ServiceEntity;

import java.math.BigDecimal;
import java.util.Set;

public class CartViewModel {

    private Set<ProductEntity> products;
    private Set<ServiceEntity> services;

    public CartViewModel() {
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

    public boolean isEmpty() {
        return products.isEmpty() && services.isEmpty();
    }
}
