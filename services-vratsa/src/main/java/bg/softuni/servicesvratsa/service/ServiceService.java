package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;

import java.util.List;

public interface ServiceService {

    void initServices();

    void addNewService(Long pictureId, ServicesServiceModel servicesServiceModel);

    List<ServicesServiceModel> findAllServices();
}
