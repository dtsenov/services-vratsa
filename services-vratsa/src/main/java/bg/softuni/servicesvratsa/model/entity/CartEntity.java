package bg.softuni.servicesvratsa.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "product_id", nullable = false)
    private String productId;

    private Integer quantity;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER)
    private Set<UserEntity> clients;

    public CartEntity() {
        quantity = 0;
        clients = new HashSet<>();
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserEntity> getClients() {
        return clients;
    }

    public void setClients(Set<UserEntity> clients) {
        this.clients = clients;
    }
}
