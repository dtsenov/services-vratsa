package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.exception.UserNotFoundException;
import bg.softuni.servicesvratsa.model.entity.RoleEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.enums.RoleNameEnum;
import bg.softuni.servicesvratsa.model.service.UserServiceModel;
import bg.softuni.servicesvratsa.model.view.UserViewModel;
import bg.softuni.servicesvratsa.model.view.WorkerViewModel;
import bg.softuni.servicesvratsa.repository.RoleRepository;
import bg.softuni.servicesvratsa.repository.UserRepository;
import bg.softuni.servicesvratsa.service.UserService;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void initUsers() {

        if (userRepository.count() != 0) {
            return;
        }

        UserEntity user = new UserEntity();

        for (int i = 0; i < 6; i++) {

            switch (i) {
                case 0:
                    user.setId(1L);
                    user.setFirstName("Денислав");
                    user.setLastName("Ценов");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.BOSS));
                    user.setEmail("denislav@example.com");
                    user.setUsername("denislav");
                    user.setPassword(passwordEncoder.encode("cenov"));
                    user.setPhoneNumber("+359848444444");
                    user.setAddress("Дъбника, бл. 20, вх. Ж, ап. 20");
                    user.setAge(30);
                    break;
                case 1:
                    user.setId(2L);
                    user.setFirstName("Христо");
                    user.setLastName("Ангелов");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.WORKER));
                    user.setEmail("hristo@example.com");
                    user.setUsername("hristo");
                    user.setPassword(passwordEncoder.encode("hristo"));
                    user.setPhoneNumber("+359848444433");
                    user.setAddress("Дъбника, бл. 7, вх. Ж, ап. 20");
                    user.setAge(25);
                    break;
                case 2:
                    user.setId(3L);
                    user.setFirstName("Георги");
                    user.setLastName("Георгиев");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.CLIENT));
                    user.setEmail("gosho@example.com");
                    user.setUsername("gosho");
                    user.setPassword(passwordEncoder.encode("gosho"));
                    user.setPhoneNumber("+359848444422");
                    user.setAddress("Дъбника, бл. 8, вх. А, ап. 20");
                    user.setAge(19);
                    break;
                case 3:
                    user.setId(4L);
                    user.setFirstName("Илиян");
                    user.setLastName("Илиев");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.WORKER));
                    user.setEmail("ilian@example.com");
                    user.setUsername("ilian");
                    user.setPassword(passwordEncoder.encode("ilian"));
                    user.setPhoneNumber("+359848444456");
                    user.setAddress("Дъбника, бл. 4, вх. Б, ап. 5");
                    user.setAge(30);
                    break;
                case 4:
                    user.setId(5L);
                    user.setFirstName("Тодор");
                    user.setLastName("Тодоров");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.CLIENT));
                    user.setEmail("tosho@example.com");
                    user.setUsername("todor");
                    user.setPassword(passwordEncoder.encode("todor"));
                    user.setPhoneNumber("+359848444457");
                    user.setAddress("Дъбника, бл. 2, вх. В, ап. 2");
                    user.setAge(26);
                    break;
                case 5:
                    user.setId(6L);
                    user.setFirstName("Петър");
                    user.setLastName("Петров");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.CLIENT));
                    user.setEmail("petyr@example.com");
                    user.setUsername("petyr");
                    user.setPassword(passwordEncoder.encode("petyr"));
                    user.setPhoneNumber("+359848444458");
                    user.setAddress("Дъбника, бл. 13, вх. Б, ап. 5");
                    user.setAge(23);
                    break;
            }

            userRepository.save(user);

        }


    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        RoleEntity role = roleRepository.findByRole(RoleNameEnum.CLIENT);

        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<UserViewModel> findAllWorkers() {
        List<UserEntity> allByRole = userRepository
                .findAllByRole(roleRepository.findByRole(RoleNameEnum.WORKER));

        List<UserViewModel> allWorkers = new ArrayList<>();

        allByRole
                .forEach(userEntity -> {
                    UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);
                    allWorkers.add(userViewModel);
                });

        return allWorkers;
    }

    @Override
    public List<UserViewModel> findAllClients() {
        List<UserEntity> allByRole = userRepository.findAllByRole(roleRepository.findByRole(RoleNameEnum.CLIENT));

        List<UserViewModel> allClients = new ArrayList<>();

        allByRole
                .forEach(userEntity -> {
                    UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);
                    allClients.add(userViewModel);
                });

        return allClients;
    }

    @Override
    public void hireWorker(Long clientId) {

        UserEntity userEntity = userRepository.findById(clientId).orElse(null);

        if (userEntity == null) {
            throw new UserNotFoundException("Потребителят, който се опитвате да достъпите с ID: " + clientId + " неможе да бъде открит!");
        }

        userEntity.setRole(roleRepository.findByRole(RoleNameEnum.WORKER));
        userRepository.save(userEntity);
    }

    @Override
    public void fireWorker(Long workerId) {

        UserEntity userEntity = userRepository.findById(workerId).orElse(null);

        if (userEntity == null) {
            throw new UserNotFoundException("Потребителят, който се опитвате да достъпите с ID: " + workerId + " неможе да бъде открит!");
        }

        userEntity.setRole(roleRepository.findByRole(RoleNameEnum.CLIENT));
        userRepository.save(userEntity);
    }

    @Override
    public boolean checkUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.isPresent();
    }

    @Override
    public boolean checkEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.isPresent();
    }

}
