package bg.softuni.servicesvratsa.model.binding;

public class AddToCartDTO {
    private Integer quantity;
    private Long productId;

    public AddToCartDTO() {
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
