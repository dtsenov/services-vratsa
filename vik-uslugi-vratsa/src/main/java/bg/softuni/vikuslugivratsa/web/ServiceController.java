package bg.softuni.vikuslugivratsa.web;

import bg.softuni.vikuslugivratsa.model.binding.ServiceAddBindingModel;
import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import bg.softuni.vikuslugivratsa.repository.PictureRepository;
import bg.softuni.vikuslugivratsa.service.CloudinaryService;
import bg.softuni.vikuslugivratsa.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;
    private final PictureRepository pictureRepository;

    public ServiceController(CloudinaryService cloudinaryService, PictureService pictureService, PictureRepository pictureRepository) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
        this.pictureRepository = pictureRepository;
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
    public String addServiceConfirm(ServiceAddBindingModel serviceAddBindingModel) throws IOException {

        PictureEntity picture = pictureService.createPictureEntity
                (serviceAddBindingModel.getPicture(), serviceAddBindingModel.getPicture().getName());


        pictureRepository.save(picture);

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
