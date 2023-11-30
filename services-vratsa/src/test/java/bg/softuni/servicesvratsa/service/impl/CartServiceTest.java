package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.repository.CartRepository;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.ProductService;
import bg.softuni.servicesvratsa.service.ServiceService;
import bg.softuni.servicesvratsa.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    private CartService serviceToTest;

    @Mock
    private CartRepository mockCartRepository;

    @Mock
    private  UserService mockUserService;

    @Mock
    private  ProductService mockProductService;

    @Mock
    private  ModelMapper mockModelMapper;

    @Mock
    private  ServiceService mockServiceService;

    void setUp() {
        serviceToTest = new CartServiceImpl(mockCartRepository,
                mockUserService, mockProductService, mockModelMapper, mockServiceService);
    }

    @Test
    void testEmptyCart() {

        when(mockCartRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<CartEntity> cart = mockCartRepository.findAll();

        Assertions.assertEquals(0, cart.size());
    }

    @Test
    void testNonEmptyCart() {

        List<CartEntity> testCartEntities = cartEntities();

        when(mockCartRepository.findAll())
                .thenReturn(testCartEntities);

        List<CartViewModel> findAllInCart = serviceToTest.findAllInCart();

        //TODO
    }

    private List<CartEntity> cartEntities() {
        List<CartEntity> cart = new ArrayList<>();

        CartEntity waterMeter = new CartEntity();
        waterMeter.setName("Water meter");
        waterMeter.setQuantity(2);
        waterMeter.setProductId("dsadshkwqhdasda");
        waterMeter.setPrice(BigDecimal.valueOf(12));
        waterMeter.setUsername("gosho");
        waterMeter.setId(1L);

        CartEntity waterTap = new CartEntity();
        waterMeter.setName("Water tap");
        waterMeter.setQuantity(3);
        waterMeter.setProductId("dsaxczxczxc");
        waterMeter.setPrice(BigDecimal.valueOf(11));
        waterMeter.setUsername("tosho");
        waterMeter.setId(2L);

        cart.add(waterMeter);
        cart.add(waterTap);

        return cart;
    }
}
