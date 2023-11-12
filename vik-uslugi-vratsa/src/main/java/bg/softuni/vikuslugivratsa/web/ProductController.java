package bg.softuni.vikuslugivratsa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("all-products")
    public String allProducts() {
        return "all-products";
    }

    @GetMapping("/add")
    public String add() {
        return "add-product";
    }

    @PostMapping
    public String addConfirm() {
        return "redirect:all-products";
    }

    @GetMapping("/water-meters")
    public String waterMeters() {
        return "product-water-meters";
    }

    @GetMapping("water-taps")
    public String waterTaps() {
        return "product-water-taps";
    }

    @GetMapping("/plumbing-wrenches")
    public String plumbingWrenches() {
        return "product-plumbing-wrenches";
    }
}
