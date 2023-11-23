package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.binding.AddToCartDTO;
import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;
import bg.softuni.servicesvratsa.repository.CartRepository;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.ProductService;
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

    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductService productService, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartViewModel> findAllInCart() {
        return cartRepository.findAll()
                .stream()
                .map(cartEntity -> modelMapper.map(cartEntity, CartViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Double totalPrice() {

        Double totalSum = 0.00;

        for (CartEntity cartEntity : cartRepository.findAll()) {
            ProductCurrentViewModel productById = productService.findProductById(cartEntity.getProductId());

            totalSum += Double.parseDouble(String.valueOf(productById.getPrice()));
        }
        return totalSum;
    }

    @Override
    public void deleteFromCart(Long id, CartViewModel cartViewModel) {
        cartRepository.deleteById(cartViewModel.getId());

    }

    @Override
    public void addToCart(String username, AddToCartDTO addToCartDTO) {

        CartEntity cartEntity = cartRepository.findByProductId(addToCartDTO.getProductId());
        UserEntity user = userService.findByUsername(username);


        if (cartEntity != null) {
            cartEntity.setQuantity(cartEntity.getQuantity() + 1);
        } else {
            cartEntity = modelMapper.map(addToCartDTO, CartEntity.class);
            cartEntity.setQuantity(1);
            cartEntity.setUsername(username);
            cartEntity.getClients().add(user);
        }
        cartRepository.save(cartEntity);
    }

    @Override
    public List<CartViewModel> getAllProductsByUser(String username) {


        List<CartEntity> allProductsInCart = cartRepository.findAllByUsername(username);
        List<CartViewModel> cartViewModelList = new ArrayList<>();

        allProductsInCart.forEach(product -> {
            CartViewModel cartViewModel = modelMapper.map(product, CartViewModel.class);
            ProductCurrentViewModel currentProduct = productService.findProductById(product.getProductId());

            cartViewModel.setName(currentProduct.getName());
            cartViewModel.setPrice(currentProduct.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
            cartViewModel.setQuantity(product.getQuantity());

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


}
