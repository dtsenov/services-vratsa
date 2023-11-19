package bg.softuni.servicesvratsa.model.binding;

import bg.softuni.servicesvratsa.model.entity.CartEntity;

public class AddToCartDTO {

    private Long id;
    private Long userId;
    private Integer quantity;
    private CartEntity product;

    public AddToCartDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CartEntity getProduct() {
        return product;
    }

    public void setProduct(CartEntity product) {
        this.product = product;
    }
}
