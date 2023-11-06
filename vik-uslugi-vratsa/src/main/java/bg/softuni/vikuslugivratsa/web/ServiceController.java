package bg.softuni.vikuslugivratsa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @GetMapping("/water-and-plumbing-installations")
    public String waterAndPlumbingInstallations() {
        return "water-and-plumbing-installations";
    }

    @GetMapping("/leaks")
    public String leaks() {
        return "leaks";
    }

    @GetMapping("/bathroom-leak-repair")
    public String bathroomLeakRepair() {
        return "bathroom-leak-repair";
    }

    @GetMapping("/toilet-cistern-repair")
    public String toiletCisternRepair() {
        return "toilet-cistern-repair";
    }

    @GetMapping("/faucet-replacement")
    public String faucetReplacement() {
        return "faucet-replacement";
    }

    @GetMapping("/water-meter-replacement")
    public String waterMeterReplacement() {
        return "water-meter-replacement";
    }
}
