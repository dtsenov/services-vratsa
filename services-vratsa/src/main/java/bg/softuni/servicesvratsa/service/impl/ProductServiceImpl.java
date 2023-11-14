package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.model.entity.ProductEntity;
import bg.softuni.servicesvratsa.model.enums.ProductTypeEnum;
import bg.softuni.servicesvratsa.model.service.ProductServiceModel;
import bg.softuni.servicesvratsa.model.view.ProductAllViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;
import bg.softuni.servicesvratsa.repository.ProductRepository;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ProductService;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void initProducts() {

        if (productRepository.count() != 0) {
            return;
        }

        ProductEntity product = new ProductEntity();

        for (int i = 0; i < 6; i++) {

            switch (i) {
                case 0:
                    product.setId(1L);
                    product.setName("Водомер POWOGAZ");
                    product.setPrice(BigDecimal.valueOf(36.50));
                    product.setBrand("POWOGAZ");
                    product.setType(ProductTypeEnum.WATER_METER);
                    product.setDescription("Водомер за студена вода. 3/4");
                    product.setPictureId(5L);
                    break;
                case 1:
                    product.setId(2L);
                    product.setName("Водомер ZENNER");
                    product.setPrice(BigDecimal.valueOf(34.80));
                    product.setBrand("ZENNER");
                    product.setType(ProductTypeEnum.WATER_METER);
                    product.setDescription("Водомер за студена вода. 3/4");
                    product.setPictureId(6L);
                    break;
                case 2:
                    product.setId(3L);
                    product.setName("Сферичен кран SAKAR");
                    product.setPrice(BigDecimal.valueOf(13.23));
                    product.setBrand("SAKAR");
                    product.setType(ProductTypeEnum.WATER_TAP);
                    product.setDescription("Сферичен кран с изпускател и присъединителен размер 1/2");
                    product.setPictureId(7L);
                    break;
                case 3:
                    product.setId(4L);
                    product.setName("Сферичен кран T ARCO");
                    product.setPrice(BigDecimal.valueOf(12.40));
                    product.setBrand("T ARCO");
                    product.setType(ProductTypeEnum.WATER_TAP);
                    product.setDescription("Сферичен кран с изпускател и присъединителен размер 3/4");
                    product.setPictureId(8L);
                    break;
                case 4:
                    product.setId(5L);
                    product.setName("Водопроводен ключ Rothenberger");
                    product.setPrice(BigDecimal.valueOf(40.50));
                    product.setBrand("ROTHENBERGER");
                    product.setType(ProductTypeEnum.PLUMBING_WRENCH);
                    product.setDescription("Тръбният ключ ROTHENBERGER е изработен от прецизно закалена хром-ванадиева стомана. " +
                            "Дръжката е с меко покритие за максимален комфорт по време на работа. " +
                            "Зъбите са преминали допълнително индукционно закаляване за повече издръжливост и по надежден захват.");
                    product.setPictureId(9L);
                    break;
                case 5:
                    product.setId(6L);
                    product.setName("Водопроводен ключ двойно рамо TOPMASTER");
                    product.setPrice(BigDecimal.valueOf(35.70));
                    product.setBrand("TOPMASTER");
                    product.setType(ProductTypeEnum.PLUMBING_WRENCH);
                    product.setDescription("Тръбният ключ TOPMASTER е изработен от прецизно закалена хром-ванадиева стомана. " +
                            "Дръжката е с меко покритие за максимален комфорт по време на работа. " +
                            "Зъбите са преминали допълнително индукционно закаляване за повече издръжливост и по надежден захват.");
                    product.setPictureId(10L);
                    break;

            }

            productRepository.save(product);

        }
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

