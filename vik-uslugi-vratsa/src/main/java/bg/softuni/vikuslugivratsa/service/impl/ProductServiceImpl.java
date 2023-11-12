package bg.softuni.vikuslugivratsa.service.impl;

import bg.softuni.vikuslugivratsa.model.entity.ProductEntity;
import bg.softuni.vikuslugivratsa.model.service.ProductServiceModel;
import bg.softuni.vikuslugivratsa.repository.ProductRepository;
import bg.softuni.vikuslugivratsa.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewProduct(Long pictureId, ProductServiceModel productServiceModel) {

        ProductEntity product = modelMapper.map(productServiceModel, ProductEntity.class);

        product.setPictureId(pictureId);

        productRepository.save(product);
    }
}
