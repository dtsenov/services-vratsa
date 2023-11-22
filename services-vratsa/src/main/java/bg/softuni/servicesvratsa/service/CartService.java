package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.binding.AddToCartDTO;
import bg.softuni.servicesvratsa.model.view.CartViewModel;

import java.util.List;

public interface CartService {


    List<CartViewModel> findAllInCart();

    Double totalPrice();

    void deleteFromCart(Long id, CartViewModel cartViewModel);

    void addToCart(String username, AddToCartDTO addToCartDTO);

    List<CartViewModel> getAllProductsByUser(String username);

    void deleteProduct(Long productId);
}
