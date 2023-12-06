package bg.softuni.servicesvratsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServicesVratsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesVratsaApplication.class, args);
    }

}
