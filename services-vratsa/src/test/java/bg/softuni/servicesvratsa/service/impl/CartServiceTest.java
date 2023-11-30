package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.exception.ProductNotFoundException;
import bg.softuni.servicesvratsa.model.binding.AddToCartDTO;
import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;
import bg.softuni.servicesvratsa.repository.CartRepository;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.ProductService;
import bg.softuni.servicesvratsa.service.ServiceService;
import bg.softuni.servicesvratsa.service.UserService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    private CartService serviceToTest;

    @Mock
    private CartRepository mockCartRepository;

    @Mock
    private UserService mockUserService;

    @Mock
    private ProductService mockProductService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private ServiceService mockServiceService;

    @BeforeEach
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

        CartEntity cartEntity = createCartEntity();
        List<CartEntity> cart = new ArrayList<>();
        cart.add(cartEntity);

        when(mockCartRepository.findAll())
                .thenReturn(cart);

        List<CartViewModel> allInCart = serviceToTest.findAllInCart();

        Assertions.assertEquals(1, allInCart.size());
        Assertions.assertFalse(allInCart.isEmpty());
    }

    @Test
    void testAddProductToCartProductNotFound() {
        String username = "pesho";
        AddToCartDTO addToCartDTO = new AddToCartDTO();
        addToCartDTO.setProductId("nonexistentProductId");

        when(mockProductService.findProductByProductId(anyString()))
                .thenThrow(new ProductNotFoundException("Product not found"));

        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            serviceToTest.addProductToCart(username, addToCartDTO);
        });

        verify(mockCartRepository, never()).save(any(CartEntity.class));
    }

    @Test
    void testAddProductToCartNewCart() {
        String username = "pesho";
        AddToCartDTO addToCartDTO = new AddToCartDTO();
        addToCartDTO.setProductId("newProductId");

        UserEntity user = new UserEntity();
        user.setUsername(username);

        when(mockCartRepository.findByProductId(anyString()))
                .thenReturn(Optional.empty());

        when(mockUserService.findByUsername(username))
                .thenReturn(user);

        ProductCurrentViewModel product = new ProductCurrentViewModel();
        product.setName("pizza");
        product.setPrice(BigDecimal.TEN);
        product.setProductId("newProductId");

        when(mockProductService.findProductByProductId(anyString()))
                .thenReturn(product);

        serviceToTest.addProductToCart(username, addToCartDTO);

        verify(mockCartRepository, times(1)).save(any(CartEntity.class));
    }

    @Test
    void testAddProductToCartExistingCart() {
        String username = "pesho";
        AddToCartDTO addToCartDTO = new AddToCartDTO();
        addToCartDTO.setProductId("existingProductId");

        CartEntity existingCartEntity = new CartEntity();
        existingCartEntity.setProductId("existingProductId");

        UserEntity user = new UserEntity();
        user.setUsername(username);

        when(mockCartRepository.findByProductId(anyString()))
                .thenReturn(Optional.of(existingCartEntity));

        when(mockUserService.findByUsername(username))
                .thenReturn(user);

        serviceToTest.addProductToCart(username, addToCartDTO);

        verify(mockCartRepository, times(1)).save(existingCartEntity);
    }


    private CartEntity createCartEntity() {

        CartEntity cartEntity = new CartEntity();
        cartEntity.setName("Water meter");
        cartEntity.setQuantity(2);
        cartEntity.setProductId("dsadshkwqhdasda");
        cartEntity.setPrice(BigDecimal.valueOf(12));
        cartEntity.setUsername("gosho");
        cartEntity.setId(1L);

        return cartEntity;
    }
}
