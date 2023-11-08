package bg.softuni.vikuslugivratsa.init;

import bg.softuni.vikuslugivratsa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDatabaseInit implements CommandLineRunner {

   private final UserService userService;

    public UserDatabaseInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.initUsers();

    }
}
