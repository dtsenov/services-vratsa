package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.entity.ProductEntity;
import bg.softuni.servicesvratsa.model.service.ProductServiceModel;
import bg.softuni.servicesvratsa.model.view.ProductAllViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;

import java.util.List;

public interface ProductService {
    void initProducts();

    void addNewProduct(Long pictureId, ProductServiceModel productServiceModel);

    List<ProductAllViewModel> getAllProducts();

    ProductCurrentViewModel findProductByProductId(String productId);

    ProductServiceModel findProductById(Long id);
}
