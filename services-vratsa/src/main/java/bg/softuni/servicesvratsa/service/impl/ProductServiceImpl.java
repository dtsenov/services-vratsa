package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.model.entity.ProductEntity;
import bg.softuni.servicesvratsa.model.service.ProductServiceModel;
import bg.softuni.servicesvratsa.model.view.ProductAllViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;
import bg.softuni.servicesvratsa.repository.ProductRepository;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ProductService;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, PictureService pictureService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
    }

    @Override
    public void addNewProduct(Long pictureId, ProductServiceModel productServiceModel) {

        ProductEntity product = modelMapper.map(productServiceModel, ProductEntity.class);

        product.setPictureId(pictureId);

        productRepository.save(product);
    }

    @Override
    public List<ProductAllViewModel> getAllProducts() {

        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> {
                    ProductAllViewModel product = modelMapper.map(productEntity, ProductAllViewModel.class);
                    PictureEntity picture = pictureService.findPictureById(productEntity.getPictureId());

                    product.setPictureTitle(picture.getTitle());
                    product.setPictureUrl(picture.getUrl());

                    return product;

                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductCurrentViewModel findProductById(Long id) {

        ProductEntity productEntity = productRepository.findById(id).orElse(null); //TODO THROW EXCEPTION
        ProductCurrentViewModel productCurrentViewModel = modelMapper.map(productEntity, ProductCurrentViewModel.class);

        if (productEntity != null) {
            PictureEntity picture = pictureService.findPictureById(productEntity.getPictureId());

            productCurrentViewModel.setPictureTitle(picture.getTitle());
            productCurrentViewModel.setPictureUrl(picture.getUrl());
        }

        return productCurrentViewModel;
    }
}

