package bg.softuni.servicesvratsa.model.binding;

public class AddToCartDTO {
    private Integer quantity;
    private String productId;

    public AddToCartDTO() {
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
