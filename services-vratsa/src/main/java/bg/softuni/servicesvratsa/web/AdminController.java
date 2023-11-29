package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.view.UserViewModel;
import bg.softuni.servicesvratsa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/workers")
    public String workers(Model model) {

        List<UserViewModel> workers = userService.findAllWorkers();

        model.addAttribute("workers", workers);

        return "workers";
    }

    @GetMapping("/clients")
    public String clients(Model model) {

        List<UserViewModel> clients = userService.findAllClients();

        model.addAttribute("clients", clients);

        return "clients";
    }
}
