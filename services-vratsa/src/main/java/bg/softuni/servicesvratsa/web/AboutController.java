package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.service.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }
 }
