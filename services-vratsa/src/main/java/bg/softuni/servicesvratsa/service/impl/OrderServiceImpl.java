package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.OrderEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.view.OrderViewModel;
import bg.softuni.servicesvratsa.repository.OrderRepository;
import bg.softuni.servicesvratsa.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void completeOrder(UserEntity user, List<OrderViewModel> orderViewModels) {

        List<OrderEntity> orderEntities = new ArrayList<>();

        orderViewModels.forEach(
                product -> {
                    OrderEntity orderEntity = modelMapper.map(product, OrderEntity.class);
                    user.getOrders().add(orderEntity);
                    orderEntity.setUser(user);
                    orderEntities.add(orderEntity);
                }
        );

        orderRepository.saveAll(orderEntities);

    }
}
