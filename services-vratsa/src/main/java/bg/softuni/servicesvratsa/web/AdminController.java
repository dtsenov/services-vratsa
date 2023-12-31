package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.view.ContactViewModel;
import bg.softuni.servicesvratsa.model.view.UserViewModel;
import bg.softuni.servicesvratsa.service.ContactService;
import bg.softuni.servicesvratsa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ContactService contactService;

    public AdminController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
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

    @PostMapping("/clients/hire")
    public String hireWorker(@RequestParam ("clientId") Long clientId) {
        userService.hireWorker(clientId);
        return "redirect:/admin/clients";
    }

    @PostMapping("/workers/fire")
    public String fireWorker(@RequestParam("workerId") Long workerId) {
        userService.fireWorker(workerId);
        return "redirect:/admin/workers";
    }

    @GetMapping("/messages")
    public String messages(Model model) {

        List<ContactViewModel> allNotAnsweredMessages = contactService.getAllUnreadMessages();
        List<ContactViewModel> allAnsweredMessages = contactService.getAllAnsweredMessages();

        model.addAttribute("allNotAnsweredMessages", allNotAnsweredMessages);
        model.addAttribute("allAnsweredMessages", allAnsweredMessages);

        return "admin-messages";
    }

    @PostMapping("/messages/read")
    public String markAsRead(@RequestParam ("messageId") Long messageId) {

        contactService.markMessageAsReadById(messageId);

        return "redirect:/admin/messages";
    }

}
