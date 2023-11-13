package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.binding.ServiceAddBindingModel;
import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.repository.PictureRepository;
import bg.softuni.servicesvratsa.service.CloudinaryService;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ServiceService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;
    private final PictureRepository pictureRepository;
    private final ServiceService serviceService;
    private final ModelMapper modelMapper;

    public ServiceController(CloudinaryService cloudinaryService, PictureService pictureService, PictureRepository pictureRepository, ServiceService serviceService, ModelMapper modelMapper) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
        this.pictureRepository = pictureRepository;
        this.serviceService = serviceService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public ServiceAddBindingModel serviceAddBindingModel() {
        return new ServiceAddBindingModel();
    }

    @GetMapping("/add")
    public String addService() {
        return "add-service";
    }


    @GetMapping("/all-services")
    public String allServices() {
        return "all-services";
    }

    @PostMapping("/add")
    public String addServiceConfirm(@Valid ServiceAddBindingModel serviceAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors() || serviceAddBindingModel.getPicture().isEmpty()) {

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

        return "redirect:all-services";

    }


    @GetMapping("/water-and-plumbing-installations")
    public String waterAndPlumbingInstallations() {
        return "service-water-and-plumbing-installations";
    }

    @GetMapping("/leaks")
    public String leaks() {
        return "leaks";
    }

    @GetMapping("/bathroom-leak-repair")
    public String bathroomLeakRepair() {
        return "service-bathroom-leak-repair";
    }

    @GetMapping("/toilet-cistern-repair")
    public String toiletCisternRepair() {
        return "service-toilet-cistern-repair";
    }

    @GetMapping("/faucet-replacement")
    public String faucetReplacement() {
        return "service-faucet-replacement";
    }

    @GetMapping("/water-meter-replacement")
    public String waterMeterReplacement() {
        return "service-water-meter-replacement";
    }


}
