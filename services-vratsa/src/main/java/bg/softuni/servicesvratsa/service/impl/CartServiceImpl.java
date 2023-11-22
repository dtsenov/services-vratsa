package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.binding.AddToCartDTO;
import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;
import bg.softuni.servicesvratsa.repository.CartRepository;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public CartServiceImpl(CartRepository cartRepository, ProductService productService, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
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

        ProductCurrentViewModel viewModel = productService.findProductById(addToCartDTO.getProductId());

        CartEntity cartEntity = modelMapper.map(
                viewModel, CartEntity.class);

        cartEntity.setProductId(viewModel.getId());

        cartEntity.setUsername(username);

        cartRepository.save(cartEntity);

    }

    @Override
    public List<CartViewModel> getAllProductsByUser(String username) {


        List<CartEntity> allProductsInCart = cartRepository.findAllByUsername(username);
        List<CartViewModel> cartViewModelList = new ArrayList<>();

        allProductsInCart.forEach(product -> {
            CartViewModel cartViewModel = modelMapper.map(product, CartViewModel.class);
            cartViewModel.setName(productService.findProductById(product.getProductId()).getName());
            cartViewModel.setPrice(productService.findProductById(product.getProductId()).getPrice());
            cartViewModelList.add(cartViewModel);
        });

        return cartViewModelList;
    }

    @Override
    public void deleteProduct(Long productId) {
        cartRepository.deleteById(productId);
    }


}
