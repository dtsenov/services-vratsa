package bg.softuni.vikuslugivratsa.web;

import bg.softuni.vikuslugivratsa.model.binding.ProductAddBindingModel;
import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import bg.softuni.vikuslugivratsa.model.service.ProductServiceModel;
import bg.softuni.vikuslugivratsa.repository.PictureRepository;
import bg.softuni.vikuslugivratsa.service.PictureService;
import bg.softuni.vikuslugivratsa.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final PictureService pictureService;
    private final PictureRepository pictureRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(PictureService pictureService, PictureRepository pictureRepository, ProductService productService, ModelMapper modelMapper) {
        this.pictureService = pictureService;
        this.pictureRepository = pictureRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }

    @GetMapping("all-products")
    public String allProducts() {
        return "all-products";
    }

    @GetMapping("/add")
    public String add() {
        return "add-product";
    }

    @PostMapping("/add")
    public String addConfirm(ProductAddBindingModel productAddBindingModel) throws IOException {

        PictureEntity picture = pictureService.createPictureEntity
                (productAddBindingModel.getPicture(), productAddBindingModel.getPicture().getName());


        if (picture != null) {
            pictureRepository.save(picture);
        }

        productService.addNewProduct(picture.getId(), modelMapper.map(
                productAddBindingModel, ProductServiceModel.class));


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
