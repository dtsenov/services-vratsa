package bg.softuni.vikuslugivratsa.service;

import bg.softuni.vikuslugivratsa.model.service.UserServiceModel;

public interface UserService {

    void initUsers();

    void registerUser(UserServiceModel userServiceModel);
}
