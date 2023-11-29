package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.RoleEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.enums.RoleNameEnum;
import bg.softuni.servicesvratsa.model.service.UserServiceModel;
import bg.softuni.servicesvratsa.model.view.WorkerViewModel;
import bg.softuni.servicesvratsa.repository.RoleRepository;
import bg.softuni.servicesvratsa.repository.UserRepository;
import bg.softuni.servicesvratsa.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < 4; i++) {

            switch (i) {
                case 0:
                    user.setId(1L);
                    user.setFirstName("Denislav");
                    user.setLastName("Cenov");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.BOSS));
                    user.setEmail("denislav@example.com");
                    user.setUsername("denislav");
                    user.setPassword(passwordEncoder.encode("cenov"));
                    user.setPhoneNumber("+359848444444");
                    user.setAge(30);
                    break;
                case 1:
                    user.setId(2L);
                    user.setFirstName("Тодор");
                    user.setLastName("Тодоров");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.WORKER));
                    user.setEmail("tosho@example.com");
                    user.setUsername("tosho");
                    user.setPassword(passwordEncoder.encode("tosho"));
                    user.setPhoneNumber("+359848444433");
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
                    user.setAge(19);
                    break;
                case 3:
                    user.setId(4L);
                    user.setFirstName("Илиян");
                    user.setLastName("Илиев");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.WORKER));
                    user.setEmail("tosho@example.com");
                    user.setUsername("ilian");
                    user.setPassword(passwordEncoder.encode("ilian"));
                    user.setPhoneNumber("+359848444456");
                    user.setAge(30);
                    break;
            }

            userRepository.save(user);

        }


    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        RoleEntity role = roleRepository.findByRole(RoleNameEnum.CLIENT);

        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}
