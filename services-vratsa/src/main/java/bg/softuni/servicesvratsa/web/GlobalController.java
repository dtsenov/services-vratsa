package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.ContactService;
import bg.softuni.servicesvratsa.service.ServiceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalController {

    private final ServiceService serviceService;
    private final ContactService contactService;
    private final CartService cartService;

    public GlobalController(ServiceService serviceService, ContactService contactService, CartService cartService) {
        this.serviceService = serviceService;
        this.contactService = contactService;
        this.cartService = cartService;
    }

    @ModelAttribute
    public void Global(Model model) {
        List<ServicesServiceModel> allServices = serviceService.findAllServices();
        Integer countOfUnreadMessagesForAdmin = contactService.getAllUnreadMessagesCount();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<CartViewModel> cartViewModels = cartService.getAllProductsByUser(authentication.getName());

        model.addAttribute("allServices", allServices);
        model.addAttribute("countOfUnreadMessagesForAdmin", countOfUnreadMessagesForAdmin);
        model.addAttribute("cartViewModels", cartViewModels);
    }

}
