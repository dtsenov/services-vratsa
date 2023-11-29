package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.view.WorkerViewModel;
import bg.softuni.servicesvratsa.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }


    @GetMapping("/our-specialists")
    public String workers(Model model) {

       List<WorkerViewModel> workers = workerService.findAllWorkers();
        model.addAttribute("workers", workers);

        return "our-specialists";
    }
}
