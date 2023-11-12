package bg.softuni.vikuslugivratsa.service.impl;

import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import bg.softuni.vikuslugivratsa.model.entity.ServiceEntity;
import bg.softuni.vikuslugivratsa.model.service.ServicesServiceModel;
import bg.softuni.vikuslugivratsa.repository.ServiceRepository;
import bg.softuni.vikuslugivratsa.service.ServiceService;
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
