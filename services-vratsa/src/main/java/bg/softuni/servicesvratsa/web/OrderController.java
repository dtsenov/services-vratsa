package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    private final CartService cartService;
    private final UserService userService;

    public OrderController(CartService cartService, UserService userService) {
        this.cartService = cartService;
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
}
