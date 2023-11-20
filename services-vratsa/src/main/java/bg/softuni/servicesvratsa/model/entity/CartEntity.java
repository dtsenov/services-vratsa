package bg.softuni.servicesvratsa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {

    private Long productId;
    private Integer quantity;
    private String username;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER)
    private Set<UserEntity> clients;

    public CartEntity() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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
