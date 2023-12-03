package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.exception.UserNotFoundException;
import bg.softuni.servicesvratsa.model.entity.RoleEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.enums.RoleNameEnum;
import bg.softuni.servicesvratsa.model.service.UserServiceModel;
import bg.softuni.servicesvratsa.model.view.UserViewModel;
import bg.softuni.servicesvratsa.repository.RoleRepository;
import bg.softuni.servicesvratsa.repository.UserRepository;
import bg.softuni.servicesvratsa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        serviceToTest = new UserServiceImpl(mockUserRepository,
                mockRoleRepository, mockModelMapper, mockPasswordEncoder);
    }

    @Test
    void testInitUsersFail() {

        when(mockUserRepository.count())
                .thenReturn(2L);

        serviceToTest.initUsers();

        verify(mockUserRepository, times(0))
                .save(any());
    }

    @Test
    void testInitUsersSuccess() {
        when(mockUserRepository.count())
                .thenReturn(0L);

        serviceToTest.initUsers();

        verify(mockUserRepository, times(4))
                .save(any());
    }

    @Test
    void testRegisterUser() {
        RoleEntity testRoleEntity = createRoleEntity();
        UserServiceModel testUserServiceModel = createUserServiceModel();
        UserEntity testUserEntity = createUserEntity();

        when(mockRoleRepository.findByRole(RoleNameEnum.CLIENT))
                .thenReturn(testRoleEntity);

        when(mockModelMapper.map(testUserServiceModel, UserEntity.class))
                .thenReturn(testUserEntity);

        serviceToTest.registerUser(testUserServiceModel);

        verify(mockUserRepository, times(1))
                .save(testUserEntity);

    }

    @Test
    void testFindByUsername() {
        UserEntity testUserEntity = createUserEntity();

        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        UserEntity user = serviceToTest.findByUsername(testUserEntity.getUsername());

        assertEquals("pesho", user.getUsername());
        assertEquals("Pesho", user.getFirstName());
        assertEquals(19, user.getAge());



    }

    @Test
    void testFindAllWorkers() {
        RoleEntity testRoleEntity = createRoleEntity();
        testRoleEntity.setRole(RoleNameEnum.WORKER);

        UserEntity testUserEntity = createUserEntity();

        UserViewModel testUserViewModel = createUserViewModel();

        List<UserEntity> testUserEntityList = new ArrayList<>();
        testUserEntityList.add(testUserEntity);

        when(mockUserRepository.findAllByRole(testRoleEntity))
                .thenReturn(testUserEntityList);

        when(mockRoleRepository.findByRole(RoleNameEnum.WORKER))
                .thenReturn(testRoleEntity);

        when(mockModelMapper.map(testUserEntity, UserViewModel.class))
                .thenReturn(testUserViewModel);

        List<UserViewModel> allWorkers = serviceToTest.findAllWorkers();
        UserViewModel returnedUserViewModel = allWorkers.get(0);

        assertEquals(1, allWorkers.size());
        assertEquals("pesho", returnedUserViewModel.getUsername());

    }

    @Test
    void testFindAllClients() {

        RoleEntity testRoleEntity = createRoleEntity();

        UserEntity testUserEntity = createUserEntity();

        UserViewModel testUserViewModel = createUserViewModel();

        List<UserEntity> testUserEntityList = new ArrayList<>();
        testUserEntityList.add(testUserEntity);

        when(mockUserRepository.findAllByRole(testRoleEntity))
                .thenReturn(testUserEntityList);

        when(mockRoleRepository.findByRole(testRoleEntity.getRole()))
                .thenReturn(testRoleEntity);

        when(mockModelMapper.map(testUserEntity, UserViewModel.class))
                .thenReturn(testUserViewModel);

        List<UserViewModel> allWorkers = serviceToTest.findAllClients();
        UserViewModel returnedUserViewModel = allWorkers.get(0);

        assertEquals(1, allWorkers.size());
        assertEquals("pesho", returnedUserViewModel.getUsername());

    }

    @Test
    void testHireWorkerThrows() {

        when(mockUserRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> serviceToTest.hireWorker(1L));
    }

    @Test
    void testHireWorkerSuccess() {
        UserEntity testUserEntity = createUserEntity();
        RoleEntity testRoleEntity = createRoleEntity();
        testRoleEntity.setRole(RoleNameEnum.WORKER);

        when(mockUserRepository.findById(testUserEntity.getId()))
                .thenReturn(Optional.of(testUserEntity));

        when(mockRoleRepository.findByRole(testRoleEntity.getRole()))
                .thenReturn(testRoleEntity);

        serviceToTest.hireWorker(testUserEntity.getId());

        verify(mockUserRepository, times(1))
                .save(testUserEntity);

    }

    @Test
    void testFireWorkerThrows() {

        when(mockUserRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> serviceToTest.fireWorker(1L));
    }

    @Test
    void testFireWorkerSuccess() {
        UserEntity testUserEntity = createUserEntity();
        RoleEntity testRoleEntity = createRoleEntity();

        when(mockUserRepository.findById(testUserEntity.getId()))
                .thenReturn(Optional.of(testUserEntity));

        when(mockRoleRepository.findByRole(testRoleEntity.getRole()))
                .thenReturn(testRoleEntity);

        serviceToTest.fireWorker(testUserEntity.getId());

        verify(mockUserRepository, times(1))
                .save(testUserEntity);
    }

    private UserViewModel createUserViewModel() {
        UserViewModel userViewModel = new UserViewModel();

        userViewModel.setUsername("pesho");
        userViewModel.setFirstName("Pesho");
        userViewModel.setAge(19);
        userViewModel.setEmail("pesho@services-vratsa.com");

        return userViewModel;
    }

    private UserEntity createUserEntity() {
        UserEntity user = new UserEntity();

        user.setUsername("pesho");
        user.setFirstName("Pesho");
        user.setAge(19);
        user.setEmail("pesho@services-vratsa.com");
        user.setPassword("peshopeshov");

        return user;
    }

    private UserServiceModel createUserServiceModel() {
        UserServiceModel userServiceModel = new UserServiceModel();

        userServiceModel.setUsername("pesho");
        userServiceModel.setFirstName("Pesho");
        userServiceModel.setAge(19);
        userServiceModel.setEmail("pesho@services-vratsa.com");
        userServiceModel.setPassword("peshopeshov");

        return userServiceModel;
    }

    private RoleEntity createRoleEntity() {
        RoleEntity role = new RoleEntity();
        role.setRole(RoleNameEnum.CLIENT);

        return role;
    }
}
