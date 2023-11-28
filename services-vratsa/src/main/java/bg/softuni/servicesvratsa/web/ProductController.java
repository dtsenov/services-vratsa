package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.exception.ProductNotFoundException;
import bg.softuni.servicesvratsa.model.binding.ProductAddBindingModel;
import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.model.entity.ProductEntity;
import bg.softuni.servicesvratsa.model.service.ProductServiceModel;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.model.view.ProductAllViewModel;
import bg.softuni.servicesvratsa.model.view.ProductCurrentViewModel;
import bg.softuni.servicesvratsa.service.CartService;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ProductService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/products")
public class ProductController {

    private final PictureService pictureService;
    private final ProductService productService;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    public ProductController(PictureService pictureService, ProductService productService, CartService cartService, ModelMapper modelMapper) {
        this.pictureService = pictureService;
        this.productService = productService;
        this.cartService = cartService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }

    @GetMapping("/all")
    public String allProducts(Model model) {

        List<ProductAllViewModel> allProducts = productService.getAllProducts();

        model.addAttribute("allProducts", allProducts);

        return "all-products";
    }

    @GetMapping("/add")
    public String add() {
        return "add-product";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors() || productAddBindingModel.getPicture().isEmpty()) {

            if (productAddBindingModel.getPicture().isEmpty()) {
                redirectAttributes.addFlashAttribute("isEmpty", true);
            }

            redirectAttributes
                    .addFlashAttribute("productAddBindingModel", productAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);

            return "redirect:add";
        }


        PictureEntity picture = pictureService.uploadPicture
                (productAddBindingModel.getPicture(), productAddBindingModel.getPicture().getName());


        productService.addNewProduct(picture.getId(), modelMapper.map(
                productAddBindingModel, ProductServiceModel.class));


        return "redirect:all";
    }

    @GetMapping("/all/{id}")
    public String productInfo(@PathVariable("id") Long id, Model model) {

        ProductServiceModel product = productService.findProductById(id);

        String productId = product.getProductId();

        ProductCurrentViewModel currentProduct = productService.findProductByProductId(productId);
        List<CartViewModel> cartViewModels = cartService.findAllInCart();

        model.addAttribute("currentProduct", currentProduct);
        model.addAttribute("cartViewModels", cartViewModels);

        return "product-info";
    }
}

