package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.service.ServiceService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalController {

    private final ServiceService serviceService;

    public GlobalController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @ModelAttribute
    public void Global(Model model) {
        List<ServicesServiceModel> allServices = serviceService.findAllServices();
        model.addAttribute("allServices", allServices);
    }
}
