package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.repository.CartRepository;
import bg.softuni.servicesvratsa.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartViewModel> findAllInCart() {
        return cartRepository.findAll()
                .stream()
                .map(cartEntity -> modelMapper.map(cartEntity, CartViewModel.class))
                .collect(Collectors.toList());
    }
}
