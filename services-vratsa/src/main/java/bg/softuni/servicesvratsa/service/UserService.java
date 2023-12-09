package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.service.UserServiceModel;
import bg.softuni.servicesvratsa.model.view.UserViewModel;
import bg.softuni.servicesvratsa.model.view.WorkerViewModel;

import java.util.List;

public interface UserService {

    void initUsers();

    void registerUser(UserServiceModel userServiceModel);

    UserEntity findByUsername(String username);

    List<UserViewModel> findAllWorkers();

    List<UserViewModel> findAllClients();

    void hireWorker(Long clientId);

    void fireWorker(Long workerId);

    boolean checkUsername(String username);

    boolean checkEmail(String email);
}
