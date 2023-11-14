package bg.softuni.servicesvratsa.init;

import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.RoleService;
import bg.softuni.servicesvratsa.service.ServiceService;
import bg.softuni.servicesvratsa.service.UserService;
import org.springframework.boot.CommandLineRunner;

public class DatabaseInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final ServiceService serviceService;
    private final PictureService pictureService;

    public DatabaseInit(RoleService roleService, UserService userService, ServiceService serviceService, PictureService pictureService) {
        this.roleService = roleService;
        this.userService = userService;
        this.serviceService = serviceService;
        this.pictureService = pictureService;
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.initRoles();
        userService.initUsers();
        pictureService.initPictures();
        serviceService.initServices();
    }
}
