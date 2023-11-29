package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.service.UserServiceModel;
import bg.softuni.servicesvratsa.model.view.WorkerViewModel;

import java.util.List;

public interface UserService {

    void initUsers();

    void registerUser(UserServiceModel userServiceModel);

    UserEntity findByUsername(String username);
}
