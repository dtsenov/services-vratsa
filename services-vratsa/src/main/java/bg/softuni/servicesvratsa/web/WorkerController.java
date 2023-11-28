package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.view.WorkerViewModel;
import bg.softuni.servicesvratsa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WorkerController {

    private final UserService userService;

    public WorkerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/our-specialists")
    public String ourSpecialists(Model model) {

       List<WorkerViewModel> workers = userService.findAllWorkers();
        model.addAttribute("workers", workers);

        return "our-specialists";
    }
}
