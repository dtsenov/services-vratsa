package bg.softuni.servicesvratsa.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "file:src/test/resources/application.yml")
@SpringBootTest
public class UserRegisterControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistrationUser() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("username", "pesho")
                .param("password", "toppesho")
                .param("confirmPassword", "toppesho")
                .param("firstName", "Pesho")
                .param("lastName", "Peshov")
                .param("email", "pesho@example.com")
                .param("age",  "18")
                .param("phoneNumber", "0878333333")
                .param("address", "Vratsa, Dybnika")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

    }
}
