package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void completeOrder(UserEntity user, List<OrderViewModel> orderViewModels);
}
