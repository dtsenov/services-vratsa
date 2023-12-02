package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.repository.RoleRepository;
import bg.softuni.servicesvratsa.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    private RoleService serviceToTest;

    @Mock
    private RoleRepository mockRoleRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new RoleServiceImpl(mockRoleRepository);
    }

    @Test
    void testInitRolesSuccess() {

        when(mockRoleRepository.count())
                .thenReturn(0L);

        serviceToTest.initRoles();

        verify(mockRoleRepository, times(3))
                .save(any());
    }

    @Test
    void testInitRolesNotInit() {

        when(mockRoleRepository.count())
                .thenReturn(2L);

        serviceToTest.initRoles();

        verify(mockRoleRepository, times(0))
                .save(any());
    }
}
