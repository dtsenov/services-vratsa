package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.RoleEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.enums.RoleNameEnum;
import bg.softuni.servicesvratsa.model.view.WorkerViewModel;
import bg.softuni.servicesvratsa.repository.RoleRepository;
import bg.softuni.servicesvratsa.repository.UserRepository;
import bg.softuni.servicesvratsa.service.WorkerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkerServiceTest {

    private WorkerService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        serviceToTest = new WorkerServiceImpl(mockUserRepository, mockRoleRepository, mockModelMapper);
    }

    @Test
    void testFindAllWorkers() {
        UserEntity user = createUserEntity();
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(user);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(RoleNameEnum.WORKER);

        when(mockRoleRepository.findByRole(RoleNameEnum.WORKER))
                .thenReturn(roleEntity);

        when(mockUserRepository.findAllByRole(roleEntity))
                .thenReturn(userEntities);

        when(mockModelMapper.map(user, WorkerViewModel.class))
                .thenReturn(createWorkerViewModel(user));

        List<WorkerViewModel> allWorkers = serviceToTest.findAllWorkers();

        WorkerViewModel workerViewModel = allWorkers.get(0);

        Assertions.assertEquals(1, allWorkers.size());
        Assertions.assertEquals("pesho", workerViewModel.getUsername());
        Assertions.assertEquals("Pesho", workerViewModel.getFirstName());
    }

    private WorkerViewModel createWorkerViewModel(UserEntity user) {
        WorkerViewModel workerViewModel = new WorkerViewModel();

        workerViewModel.setUsername(user.getUsername());
        workerViewModel.setPassword(user.getPassword());
        workerViewModel.setEmail(user.getEmail());
        workerViewModel.setFirstName(user.getFirstName());

        return workerViewModel;
    }


    private UserEntity createUserEntity() {
        UserEntity user = new UserEntity();

        user.setUsername("pesho");
        user.setPassword("peshopesho");
        user.setEmail("pesho@test.bg");
        user.setFirstName("Pesho");

        return user;
    }
}


