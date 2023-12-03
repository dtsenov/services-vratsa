package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.exception.ProductNotFoundException;
import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.model.entity.ServiceEntity;
import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.model.view.ServiceViewModel;
import bg.softuni.servicesvratsa.repository.ServiceRepository;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ServiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceServiceTest {

    private ServiceService serviceToTest;

    @Mock
    private ServiceRepository mockServiceRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private PictureService mockPictureService;


    @BeforeEach
    void setUp() {
        serviceToTest = new ServiceServiceImpl(mockServiceRepository, mockModelMapper, mockPictureService);
    }

    @Test
    void testInitServicesSuccess() {

        when(mockServiceRepository.count())
                .thenReturn(0L);

        serviceToTest.initServices();

        verify(mockServiceRepository, times(4))
                .save(any());
    }

    @Test
    void testInitServicesFail() {
        when(mockServiceRepository.count())
                .thenReturn(2L);

        serviceToTest.initServices();

        verify(mockServiceRepository, times(0))
                .save(any());
    }

    @Test
    void testAddNewService() {
        Long pictureId = 1L;
        ServicesServiceModel testServicesServiceModel = createServicesServiceModel();
        ServiceEntity testServiceEntity = createServiceEntity();

        when(mockModelMapper.map(testServicesServiceModel, ServiceEntity.class))
                .thenReturn(testServiceEntity);

        serviceToTest.addNewService(pictureId, testServicesServiceModel);

        verify(mockServiceRepository, times(1))
                .save(testServiceEntity);
    }

    @Test
    void testFindAllServices() {
        ServiceEntity testServiceEntity = createServiceEntity();
        List<ServiceEntity> serviceEntityList = new ArrayList<>();
        serviceEntityList.add(testServiceEntity);

        ServicesServiceModel testServicesServiceModel = createServicesServiceModel();

        when(mockModelMapper.map(testServiceEntity, ServicesServiceModel.class))
                .thenReturn(testServicesServiceModel);

        when(mockServiceRepository.findAll())
                .thenReturn(serviceEntityList);

        List<ServicesServiceModel> allServices = serviceToTest.findAllServices();
        ServicesServiceModel servicesServiceModel = allServices.get(0);

        Assertions.assertEquals("change water meter", servicesServiceModel.getName());
        Assertions.assertEquals(1, allServices.size());
    }

    @Test
    void testFindServiceByIdSuccess() {
        ServiceEntity testServiceEntity = createServiceEntity();
        ServiceViewModel testServiceViewModel = createServiceViewModel();
        PictureEntity testPictureEntity = createPictureEntity();

        when(mockServiceRepository.findById(testServiceEntity.getId()))
                .thenReturn(Optional.of(testServiceEntity));

        when(mockModelMapper.map(testServiceEntity, ServiceViewModel.class))
                .thenReturn(testServiceViewModel);

        when(mockPictureService.findPictureById(testServiceEntity.getId()))
                .thenReturn(testPictureEntity);

        ServiceViewModel serviceById = serviceToTest.findServiceById(testServiceEntity.getId());

        Assertions.assertEquals(1, serviceById.getId());
        Assertions.assertEquals("change water meter", serviceById.getName());
    }

    @Test
    void testFindServiceByIdThrows() {

        when(mockServiceRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> serviceToTest.findServiceById(1L));
    }

    @Test
    void testFindServiceByServiceIdSuccess() {
        ServiceEntity testServiceEntity = createServiceEntity();
        ServiceViewModel testServiceViewModel = createServiceViewModel();
        PictureEntity testPictureEntity = createPictureEntity();

        when(mockServiceRepository.findByServiceId(testServiceEntity.getServiceId()))
                .thenReturn(Optional.of(testServiceEntity));

        when(mockModelMapper.map(testServiceEntity, ServiceViewModel.class))
                .thenReturn(testServiceViewModel);

        when(mockPictureService.findPictureById(testServiceEntity.getId()))
                .thenReturn(testPictureEntity);

        ServiceViewModel serviceById = serviceToTest.findServiceByServiceId(testServiceEntity.getServiceId());

        Assertions.assertEquals(1, serviceById.getId());
        Assertions.assertEquals("change water meter", serviceById.getName());
    }

    @Test
    void testFindServiceByServiceIdThrows() {

        when(mockServiceRepository.findByServiceId("1L"))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> serviceToTest.findServiceByServiceId("1L"));
    }



    private ServiceViewModel createServiceViewModel() {
        ServiceViewModel serviceViewModel = new ServiceViewModel();

        serviceViewModel.setName("change water meter");
        serviceViewModel.setPrice(BigDecimal.TEN);
        serviceViewModel.setId(1L);

        return serviceViewModel;
    }

    private ServiceEntity createServiceEntity() {
        ServiceEntity serviceEntity = new ServiceEntity();

        serviceEntity.setName("change water meter");
        serviceEntity.setPrice(BigDecimal.TEN);
        serviceEntity.setId(1L);
        serviceEntity.setPictureId(1L);

        return serviceEntity;
    }

    private ServicesServiceModel createServicesServiceModel() {
        ServicesServiceModel servicesServiceModel = new ServicesServiceModel();

        servicesServiceModel.setName("change water meter");
        servicesServiceModel.setId(1L);
        servicesServiceModel.setPrice(BigDecimal.TEN);

        return servicesServiceModel;
    }

    private PictureEntity createPictureEntity() {
        PictureEntity picture = new PictureEntity();

        picture.setTitle("change");
        picture.setId(1L);
        picture.setPublicId("change");

        return picture;
    }
}
