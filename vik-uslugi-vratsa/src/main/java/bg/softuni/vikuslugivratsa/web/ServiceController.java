package bg.softuni.vikuslugivratsa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @GetMapping("/add")
    public String addService() {
        return "add-service";
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
