package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.service.ContactService;
import bg.softuni.servicesvratsa.service.ServiceService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalController {

    private final ServiceService serviceService;
    private final ContactService contactService;

    public GlobalController(ServiceService serviceService, ContactService contactService) {
        this.serviceService = serviceService;
        this.contactService = contactService;
    }

    @ModelAttribute
    public void Global(Model model) {
        List<ServicesServiceModel> allServices = serviceService.findAllServices();
        Integer countOfUnreadMessagesForAdmin = contactService.getAllUnreadMessagesCount();

        model.addAttribute("allServices", allServices);
        model.addAttribute("countOfUnreadMessagesForAdmin", countOfUnreadMessagesForAdmin);
    }

}
