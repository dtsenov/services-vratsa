package bg.softuni.vikuslugivratsa.service;

import bg.softuni.vikuslugivratsa.model.service.ProductServiceModel;

public interface ProductService {
    void addNewProduct(Long pictureId, ProductServiceModel productServiceModel);
}
