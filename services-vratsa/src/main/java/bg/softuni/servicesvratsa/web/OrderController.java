package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.model.view.OrderViewModel;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.OrderService;
import bg.softuni.servicesvratsa.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public OrderController(CartService cartService, OrderService orderService, ModelMapper modelMapper, UserService userService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/make-order")
    public String makeOrder(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findByUsername(authentication.getName());

        List<CartViewModel> cartViewModels = cartService.getAllProductsByUser(authentication.getName());

        model.addAttribute("cartViewModels", cartViewModels);
        model.addAttribute("user", user);

        return "make-order";
    }

    @PostMapping("/order-completed")
    public String orderCompleted() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findByUsername(authentication.getName());

        List<CartViewModel> cartViewModels = cartService.getAllProductsByUser(authentication.getName());
        List<OrderViewModel> orderViewModels = new ArrayList<>();

        cartViewModels.forEach(
                product -> {
                    OrderViewModel orderViewModel = modelMapper.map(product, OrderViewModel.class);
                    orderViewModels.add(orderViewModel);
                }
        );

        cartService.deleteAllByUser(authentication.getName());
        orderService.completeOrder(user, orderViewModels);

        return "order-completed";
    }
}
