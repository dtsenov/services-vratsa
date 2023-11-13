package bg.softuni.servicesvratsa.init;

import bg.softuni.servicesvratsa.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleDatabaseInit implements CommandLineRunner {

    private final RoleService roleService;

    public RoleDatabaseInit(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initRoles();
    }
}
