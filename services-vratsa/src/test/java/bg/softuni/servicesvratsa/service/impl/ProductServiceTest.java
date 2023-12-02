package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.exception.ProductNotFoundException;
import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.model.entity.ProductEntity;
import bg.softuni.servicesvratsa.model.service.ProductServiceModel;
import bg.softuni.servicesvratsa.model.view.ProductAllViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;
import bg.softuni.servicesvratsa.repository.ProductRepository;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductService serviceToTest;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private PictureService mockPictureService;

    @BeforeEach
    void setUp() {
        serviceToTest = new ProductServiceImpl(mockProductRepository, mockModelMapper, mockPictureService);
    }

    @Test
    void testInitProductsNotInit() {
        ProductEntity product = new ProductEntity();

        when(mockProductRepository.count())
                .thenReturn(2L);

        serviceToTest.initProducts();

        verify(mockProductRepository, times(0)).save(product);
    }

    @Test
    void testInitProductsSuccess() {

        when(mockProductRepository.count())
                .thenReturn(0L);

        serviceToTest.initProducts();

        verify(mockProductRepository, times(6)).save(any());
    }

    @Test
    void testAddNewProduct() {
        ProductEntity testProductEntity = createProductEntity();
        ProductServiceModel productServiceModel = createProductServiceModel();

        when(mockModelMapper.map(productServiceModel, ProductEntity.class))
                .thenReturn(testProductEntity);

        serviceToTest.addNewProduct(2L, productServiceModel);

        verify(mockProductRepository, times(1)).save(testProductEntity);
    }

    @Test
    void testGetAllProducts() {
        ProductEntity testProductEntity = createProductEntity();
        ProductAllViewModel testProductAllViewModel = createProductAllViewModel();
        PictureEntity testPictureEntity = createPictureEntity();
        List<ProductEntity> productEntityList = new ArrayList<>();

        productEntityList.add(testProductEntity);

        when(mockProductRepository.findAll())
                .thenReturn(productEntityList);

        when(mockModelMapper.map(testProductEntity, ProductAllViewModel.class))
                .thenReturn(testProductAllViewModel);

        when(mockPictureService.findPictureById(testPictureEntity.getId()))
                .thenReturn(testPictureEntity);

        List<ProductAllViewModel> allProducts = serviceToTest.getAllProducts();
        ProductAllViewModel productAllViewModel = allProducts.get(0);

        Assertions.assertEquals(1, allProducts.size());
        Assertions.assertEquals("water-meter", productAllViewModel.getName());


    }

    @Test
    void testFindProductByProductId() {

        ProductCurrentViewModel testProductCurrentViewModel = createProductCurrentViewModel();
        ProductEntity testProductEntity = createProductEntity();
        PictureEntity testPictureEntity = createPictureEntity();

        when(mockPictureService.findPictureById(1L))
                .thenReturn(testPictureEntity);

        when(mockModelMapper.map(testProductEntity, ProductCurrentViewModel.class))
                .thenReturn(testProductCurrentViewModel);

        when(mockProductRepository.findByProductId("water-meter"))
                .thenReturn(Optional.of(testProductEntity));

        ProductCurrentViewModel productByProductId = serviceToTest.findProductByProductId("water-meter");

        Assertions.assertEquals("water-meter", productByProductId.getName());
        Assertions.assertEquals("water-meter", productByProductId.getDescription());
        Assertions.assertEquals("water-meter-jpg", productByProductId.getPictureTitle());

    }

    @Test
    void testFindProductByIdSuccess() {
        ProductEntity testProductEntity = createProductEntity();
        ProductServiceModel testProductServiceModel = createProductServiceModel();

        when(mockProductRepository.findById(testProductEntity.getId()))
                .thenReturn(Optional.of(testProductEntity));

        when(mockModelMapper.map(testProductEntity, ProductServiceModel.class))
                .thenReturn(testProductServiceModel);

        ProductServiceModel productById = serviceToTest.findProductById(testProductEntity.getId());

        Assertions.assertEquals("water-meter", productById.getName());
        Assertions.assertEquals("water-meter", productById.getProductId());
    }

    @Test
    void testFindProductByIdThrows() {

        when(mockProductRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> serviceToTest.findProductById(1L));

    }

    private ProductCurrentViewModel createProductCurrentViewModel() {

        ProductCurrentViewModel productCurrentViewModel = new ProductCurrentViewModel();

        productCurrentViewModel.setName("water-meter");
        productCurrentViewModel.setPrice(BigDecimal.TEN);
        productCurrentViewModel.setDescription("water-meter");
        productCurrentViewModel.setPictureTitle("water-meter-jpg");

        return productCurrentViewModel;
    }

    private PictureEntity createPictureEntity() {
        PictureEntity pictureEntity = new PictureEntity();

        pictureEntity.setId(1L);
        pictureEntity.setTitle("water-meter-jpg");

        return pictureEntity;
    }

    private ProductAllViewModel createProductAllViewModel() {
        ProductAllViewModel productAllViewModel = new ProductAllViewModel();

        productAllViewModel.setName("water-meter");
        productAllViewModel.setPrice(BigDecimal.TEN);
        productAllViewModel.setDescription("water-meter");
        productAllViewModel.setPictureTitle("water-meter-jpg");

        return productAllViewModel;
    }

    private ProductEntity createProductEntity() {

        ProductEntity product = new ProductEntity();
        product.setName("water-meter");
        product.setPrice(BigDecimal.TEN);
        product.setProductId("water-meter");
        product.setPictureId(1L);

        return product;
    }

    private ProductServiceModel createProductServiceModel() {

        ProductServiceModel product = new ProductServiceModel();

        product.setName("water-meter");
        product.setPrice(BigDecimal.TEN);
        product.setProductId("water-meter");

        return product;
    }


}
