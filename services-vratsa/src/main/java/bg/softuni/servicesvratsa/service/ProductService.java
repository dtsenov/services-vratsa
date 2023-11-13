package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.service.ProductServiceModel;

public interface ProductService {
    void addNewProduct(Long pictureId, ProductServiceModel productServiceModel);
}
