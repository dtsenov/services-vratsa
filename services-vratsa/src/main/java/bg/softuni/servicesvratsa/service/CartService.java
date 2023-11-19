package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;

import java.util.List;

public interface CartService {


    List<CartViewModel> findAllInCart();

    Double totalPrice();


    void addToCart(Long id, ProductCurrentViewModel productCurrentViewModel);

    void deleteFromCart(Long id, CartViewModel cartViewModel);
}
