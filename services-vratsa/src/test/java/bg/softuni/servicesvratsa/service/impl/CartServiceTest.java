package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.exception.ProductNotFoundException;
import bg.softuni.servicesvratsa.model.binding.AddToCartDTO;
import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;
import bg.softuni.servicesvratsa.model.view.ServiceViewModel;
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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertEquals(0, cart.size());
    }

    @Test
    void testNonEmptyCart() {

        CartEntity cartEntity = createCartEntity();
        List<CartEntity> cart = new ArrayList<>();
        cart.add(cartEntity);

        when(mockCartRepository.findAll())
                .thenReturn(cart);

        List<CartViewModel> allInCart = serviceToTest.findAllInCart();

        assertEquals(1, allInCart.size());
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

    @Test
    void testAddServiceToNotEmptyCart() {
        CartEntity cartEntity = new CartEntity();

        UserEntity user = new UserEntity();
        user.setUsername("pesho");

        when(mockCartRepository.findByProductId(cartEntity.getProductId()))
                .thenReturn(Optional.of(cartEntity));

        when(mockUserService.findByUsername(user.getUsername()))
                .thenReturn(user);

        serviceToTest.addServiceToCart(user.getUsername(), cartEntity.getProductId());

        verify(mockCartRepository, times(1)).save(cartEntity);

    }

    @Test
    void testAddServiceToCarNewCart() {
        String username = "pesho";
        String serviceId = "change";
        CartEntity cartEntity = new CartEntity();
        cartEntity.setQuantity(0);
        UserEntity user = new UserEntity();
        user.setUsername(username);

        ServiceViewModel serviceViewModel = createServiceViewModel();

        when(mockCartRepository.findByProductId(serviceId))
                .thenReturn(Optional.empty());

        when(mockUserService.findByUsername(username))
                .thenReturn(user);

        when(mockServiceService.findServiceByServiceId(serviceId))
                .thenReturn(serviceViewModel);

        serviceToTest.addServiceToCart(username, serviceViewModel.getServiceId());

        verify(mockCartRepository, times(1)).save(any(CartEntity.class));
    }

    @Test
    void testDeleteProductByIdWhenQuantityIsOne() {
        CartEntity cartEntity = createCartEntity();
        when(mockCartRepository.findById(1L)).thenReturn(Optional.of(cartEntity));

        serviceToTest.deleteProduct(cartEntity.getId());

        verify(mockCartRepository, times(0)).save(cartEntity);
        verify(mockCartRepository, times(1)).deleteById(eq(cartEntity.getId()));
    }

    @Test
    void testDeleteProductByIdWHenQuantityIsMoreThanOne() {

        CartEntity cartEntity = createCartEntity();
        cartEntity.setQuantity(2);
        when(mockCartRepository.findById(1L)).thenReturn(Optional.of(cartEntity));

        serviceToTest.deleteProduct(cartEntity.getId());

        verify(mockCartRepository, times(1)).save(cartEntity);
        verify(mockCartRepository, times(0)).deleteById(cartEntity.getId());
    }

    @Test
    void testGetAllProductsByUser() {
        String username = "pesho";

        CartEntity first = createCartEntity();
        CartEntity second = createCartEntity();

        List<CartEntity> testCartEntity = new ArrayList<>();
        testCartEntity.add(first);
        testCartEntity.add(second);

        when(mockCartRepository.findAllByUsername(username))
                .thenReturn(testCartEntity);

        when(mockModelMapper.map(first, CartViewModel.class))
                .thenReturn(createCartViewModel(first));
        when(mockModelMapper.map(second, CartViewModel.class))
                .thenReturn(createCartViewModel(second));

        List<CartViewModel> allProductsByUser = serviceToTest.getAllProductsByUser(username);

        assertNotNull(allProductsByUser);
        assertEquals(2, allProductsByUser.size());
    }

    private CartViewModel createCartViewModel(CartEntity cartEntity) {
        CartViewModel cartViewModel = new CartViewModel();
        cartViewModel.setName(cartViewModel.getName());
        cartViewModel.setId(cartViewModel.getId());
        cartViewModel.setQuantity(cartEntity.getQuantity());
        cartViewModel.setPrice(cartEntity.getPrice());
        cartViewModel.setProductId(cartEntity.getProductId());

        return cartViewModel;
    }


    private CartEntity createCartEntity() {

        CartEntity cartEntity = new CartEntity();
        cartEntity.setName("Water meter");
        cartEntity.setQuantity(1);
        cartEntity.setProductId("dsadshkwqhdasda");
        cartEntity.setPrice(BigDecimal.valueOf(12));
        cartEntity.setUsername("gosho");
        cartEntity.setId(1L);

        return cartEntity;
    }

    private ServiceViewModel createServiceViewModel() {
        ServiceViewModel serviceViewModel = new ServiceViewModel();

        serviceViewModel.setName("change water meter");
        serviceViewModel.setId(1L);
        serviceViewModel.setPrice(BigDecimal.TEN);
        serviceViewModel.setServiceId("change");

        return serviceViewModel;
    }
}
