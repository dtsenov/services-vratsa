package bg.softuni.vikuslugivratsa.service.impl;

import bg.softuni.vikuslugivratsa.model.entity.RoleEntity;
import bg.softuni.vikuslugivratsa.model.entity.UserEntity;
import bg.softuni.vikuslugivratsa.model.enums.RoleNameEnum;
import bg.softuni.vikuslugivratsa.model.service.UserServiceModel;
import bg.softuni.vikuslugivratsa.repository.RoleRepository;
import bg.softuni.vikuslugivratsa.repository.UserRepository;
import bg.softuni.vikuslugivratsa.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        for (int i = 0; i < 3; i++) {

            switch (i) {
                case 0:
                    user.setId(1L);
                    user.setFirstName("Pesho");
                    user.setLastName("Petrov");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.BOSS));
                    user.setEmail("pesho@example.com");
                    user.setUsername("pesho");
                    user.setPassword(passwordEncoder.encode("pesho"));
                    user.setPhoneNumber("+359848444444");
                    user.setAge(35);
                    break;
                case 1:
                    user.setId(2L);
                    user.setFirstName("Tosho");
                    user.setLastName("Toshev");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.WORKER));
                    user.setEmail("tosho@example.com");
                    user.setUsername("tosho");
                    user.setPassword(passwordEncoder.encode("tosho"));
                    user.setPhoneNumber("+359848444433");
                    user.setAge(25);
                    break;
                case 2:
                    user.setId(3L);
                    user.setFirstName("Gosho");
                    user.setLastName("Goshev");
                    user.setRole(roleRepository.findByRole(RoleNameEnum.CLIENT));
                    user.setEmail("gosho@example.com");
                    user.setUsername("gosho");
                    user.setPassword(passwordEncoder.encode("gosho"));
                    user.setPhoneNumber("+359848444422");
                    user.setAge(19);
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
}
