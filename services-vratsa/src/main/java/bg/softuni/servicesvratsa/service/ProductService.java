package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.service.ProductServiceModel;
import bg.softuni.servicesvratsa.model.view.ProductAllViewModel;

import java.util.List;

public interface ProductService {
    void addNewProduct(Long pictureId, ProductServiceModel productServiceModel);

    List<ProductAllViewModel> getAllProducts();
}
