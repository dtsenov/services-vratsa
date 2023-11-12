package bg.softuni.vikuslugivratsa.service;

import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import bg.softuni.vikuslugivratsa.model.service.ServicesServiceModel;

public interface ServiceService {

    void addNewService(Long pictureId, ServicesServiceModel servicesServiceModel);
}
