package bg.softuni.vikuslugivratsa.init;

import bg.softuni.vikuslugivratsa.service.RoleService;
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
