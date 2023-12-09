package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.binding.ServiceAddBindingModel;
import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.model.view.ServiceViewModel;
import bg.softuni.servicesvratsa.repository.PictureRepository;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.CloudinaryService;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ServiceService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;
    private final PictureRepository pictureRepository;
    private final ServiceService serviceService;
    private final ModelMapper modelMapper;
    private final CartService cartService;
    private boolean isServiceNameExists;

    public ServiceController(CloudinaryService cloudinaryService, PictureService pictureService, PictureRepository pictureRepository, ServiceService serviceService, ModelMapper modelMapper, CartService cartService) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
        this.pictureRepository = pictureRepository;
        this.serviceService = serviceService;
        this.modelMapper = modelMapper;
        this.cartService = cartService;
        this.isServiceNameExists = false;
    }

    @ModelAttribute
    public ServiceAddBindingModel serviceAddBindingModel() {
        return new ServiceAddBindingModel();
    }

    @GetMapping("/add")
    public String addService(Model model) {
        model.addAttribute("isServiceNameExists", isServiceNameExists);
        return "add-service";
    }


    @GetMapping("/all")
    public String allServices() {
        return "all-services";
    }

    @PostMapping("/add")
    public String addServiceConfirm(@Valid ServiceAddBindingModel serviceAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) throws IOException {

        boolean isNameExists = serviceService.findServiceByName(serviceAddBindingModel.getName());

        if (bindingResult.hasErrors() || serviceAddBindingModel.getPicture().isEmpty() || isNameExists) {

            if (isNameExists) {
                isServiceNameExists = true;
            }

            if (serviceAddBindingModel.getPicture().isEmpty()) {
                redirectAttributes.addFlashAttribute("isEmpty", true);
            }

            redirectAttributes
                    .addFlashAttribute("serviceAddBindingModel", serviceAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.serviceAddBindingModel", bindingResult);

            return "redirect:add";
        }


        PictureEntity picture = pictureService.uploadPicture
                (serviceAddBindingModel.getPicture(), serviceAddBindingModel.getPicture().getName());

        serviceService.addNewService(picture.getId(), modelMapper.map(
                serviceAddBindingModel, ServicesServiceModel.class));

        return "redirect:/services/add/success";

    }

    @GetMapping("/add/success")
    public String addSuccess() {
        return "add-service-success";
    }

    @GetMapping("/all/{id}")
    public String serviceInfo(@PathVariable ("id") Long id, Model model) {

        ServiceViewModel currentService = serviceService.findServiceById(id);
        List<CartViewModel> cartViewModels = cartService.findAllInCart();

        model.addAttribute("currentService", currentService);
        model.addAttribute("cartViewModels", cartViewModels);

        return "service-info";
    }

}
