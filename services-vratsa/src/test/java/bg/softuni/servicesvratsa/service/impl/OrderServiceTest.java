package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.entity.OrderEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.view.OrderViewModel;
import bg.softuni.servicesvratsa.repository.OrderRepository;
import bg.softuni.servicesvratsa.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private OrderService serviceToTest;

    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        serviceToTest = new OrderServiceImpl(mockOrderRepository, mockModelMapper);
    }

    @Test
    void testCompleteOrder() {
        UserEntity user = new UserEntity();
        user.setUsername("pesho");

        OrderViewModel orderViewModel = createOrderViewModel();
        List<OrderViewModel> orderViewModelList = new ArrayList<>();
        orderViewModelList.add(orderViewModel);

        OrderEntity orderEntity = createOrderEntity(orderViewModel);
        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(orderEntity);


        when(mockModelMapper.map(orderViewModel, OrderEntity.class))
                .thenReturn(orderEntity);

        serviceToTest.completeOrder(user, orderViewModelList);

        verify(mockOrderRepository, times(1)).saveAll(orderEntities);


    }

    private OrderEntity createOrderEntity(OrderViewModel orderViewModel) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderEntity.getId());
        orderEntity.setQuantity(orderViewModel.getQuantity());
        orderEntity.setName(orderViewModel.getName());
        orderEntity.setPrice(orderViewModel.getPrice());
        orderEntity.setProductId(orderViewModel.getProductId());

        return orderEntity;
    }

    private OrderViewModel createOrderViewModel() {
       OrderViewModel orderViewModel = new OrderViewModel();

        orderViewModel.setId(1L);
        orderViewModel.setQuantity(1);
        orderViewModel.setName("pizza");
        orderViewModel.setPrice(BigDecimal.ONE);
        orderViewModel.setProductId("pizzapizza");

        return orderViewModel;
    }
}
