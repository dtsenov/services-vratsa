package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.ServiceEntity;
import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.repository.ServiceRepository;
import bg.softuni.servicesvratsa.service.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ModelMapper modelMapper) {
        this.serviceRepository = serviceRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addNewService(Long pictureId, ServicesServiceModel servicesServiceModel) {



        ServiceEntity service = modelMapper.map(
                servicesServiceModel, ServiceEntity.class);

        service.setPictureId(pictureId);

        serviceRepository.save(service);
    }
}
