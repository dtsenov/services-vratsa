package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.ProductEntity;
import bg.softuni.servicesvratsa.repository.ProductRepository;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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


}
