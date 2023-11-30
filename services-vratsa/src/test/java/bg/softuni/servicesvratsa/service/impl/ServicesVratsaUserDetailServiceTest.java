package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.RoleEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.enums.RoleNameEnum;
import bg.softuni.servicesvratsa.repository.RoleRepository;
import bg.softuni.servicesvratsa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicesVratsaUserDetailServiceTest {

    private ServicesVratsaUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new ServicesVratsaUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {
        assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("chocho"));
    }

    @Test
    void testUserFoundException() {
        UserEntity testUserEntity = createUserToTest();

        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        UserDetails userDetails =
                serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        assertNotNull(userDetails);
        assertEquals(testUserEntity.getUsername(), userDetails.getUsername());
    }

    private static UserEntity createUserToTest() {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName("Tosho");
        userEntity.setLastName("Toshev");
        userEntity.setUsername("tosho");
        userEntity.setEmail("tosho@gmail.com");
        userEntity.setPassword("toshotoshev");

        RoleEntity role = new RoleEntity();
        role.setRole(RoleNameEnum.WORKER);
        userEntity.setRole(role);

        return userEntity;
    }
}


