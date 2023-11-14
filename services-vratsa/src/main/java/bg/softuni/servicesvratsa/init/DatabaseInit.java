package bg.softuni.servicesvratsa.init;

import bg.softuni.servicesvratsa.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final ServiceService serviceService;
    private final PictureService pictureService;
    private final ProductService productService;

    public DatabaseInit(RoleService roleService, UserService userService, ServiceService serviceService, PictureService pictureService, ProductService productService) {
        this.roleService = roleService;
        this.userService = userService;
        this.serviceService = serviceService;
        this.pictureService = pictureService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.initRoles();
        userService.initUsers();
        pictureService.initPictures();
        serviceService.initServices();
        productService.initProducts();
    }
}
