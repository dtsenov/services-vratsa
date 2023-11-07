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
    public void registerUser(UserServiceModel userServiceModel) {
        RoleEntity role = roleRepository.findByRole(RoleNameEnum.CLIENT);

        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }
}
