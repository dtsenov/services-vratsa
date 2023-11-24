package bg.softuni.servicesvratsa.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {

    @Column(name = "product_id", nullable = false)
//    private Long productId;

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

//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }


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
