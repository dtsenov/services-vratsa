package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.service.UserServiceModel;

public interface UserService {

    void initUsers();

    void registerUser(UserServiceModel userServiceModel);

    UserEntity findByUsername(String username);
}
