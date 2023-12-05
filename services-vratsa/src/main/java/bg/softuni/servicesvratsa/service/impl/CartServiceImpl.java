package bg.softuni.servicesvratsa.service.impl;

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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final ServiceService serviceService;

    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductService productService, ModelMapper modelMapper, ServiceService serviceService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.serviceService = serviceService;
    }

    @Override
    public List<CartViewModel> findAllInCart() {
        return cartRepository.findAll()
                .stream()
                .map(cartEntity -> modelMapper.map(cartEntity, CartViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addProductToCart(String username, AddToCartDTO addToCartDTO) {

        CartEntity cartEntity = cartRepository.findByProductId(addToCartDTO.getProductId()).orElse(null);
        UserEntity user = userService.findByUsername(username);


        if (cartEntity != null) {
            cartEntity.setQuantity(cartEntity.getQuantity() + 1);
        } else {
            cartEntity = new CartEntity();

            ProductCurrentViewModel product = productService.findProductByProductId(addToCartDTO.getProductId());

            cartEntity.setName(product.getName());
            cartEntity.setPrice(product.getPrice());
            cartEntity.setQuantity(1);
            cartEntity.setProductId(product.getProductId());
            cartEntity.setUser(user);
        }
        cartRepository.save(cartEntity);
    }

    @Override
    public List<CartViewModel> getAllProductsByUser(String username) {

        UserEntity user = userService.findByUsername(username);

        List<CartEntity> allProductsInCart = cartRepository.findAllByUser(user);
        List<CartViewModel> cartViewModelList = new ArrayList<>();

        allProductsInCart.forEach(product -> {
            CartViewModel cartViewModel = modelMapper.map(product, CartViewModel.class);
            cartViewModel.setPrice(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
            cartViewModelList.add(cartViewModel);
        });

        return cartViewModelList;
    }

    @Override
    public void deleteProduct(Long id) {
        CartEntity cartEntity = cartRepository.findById(id).orElse(null);

        if (cartEntity == null) {
            return;
        }

        if (cartEntity.getQuantity() > 1) {
            cartEntity.setQuantity(cartEntity.getQuantity() - 1);
            cartRepository.save(cartEntity);
        } else {
            cartRepository.deleteById(id);
        }

    }

    @Override
    public void addServiceToCart(String username, String serviceId) {

        UserEntity user = userService.findByUsername(username);
        CartEntity cartEntity = cartRepository.findByProductId(serviceId).orElse(null);

        if (cartEntity != null) {
            cartEntity.setQuantity(cartEntity.getQuantity() + 1);
        } else {
            cartEntity = new CartEntity();

            ServiceViewModel currentService = serviceService.findServiceByServiceId(serviceId);

            cartEntity.setName(currentService.getName());
            cartEntity.setPrice(currentService.getPrice());
            cartEntity.setQuantity(1);
            cartEntity.setProductId(serviceId);
            cartEntity.setUser(user);
        }

        cartRepository.save(cartEntity);
    }

    @Override
    public void deleteAllByUser(String username) {
        UserEntity user = userService.findByUsername(username);
        cartRepository.deleteByUser(user);
    }



}
