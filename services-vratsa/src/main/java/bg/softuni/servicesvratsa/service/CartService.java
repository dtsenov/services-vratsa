package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.view.CartViewModel;

import java.util.List;

public interface CartService {


    List<CartViewModel> findAllInCart();

    Double totalPrice();
}
