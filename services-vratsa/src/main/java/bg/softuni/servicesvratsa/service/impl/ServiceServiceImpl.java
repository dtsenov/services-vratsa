package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.model.entity.ServiceEntity;
import bg.softuni.servicesvratsa.model.service.ServicesServiceModel;
import bg.softuni.servicesvratsa.model.view.ServiceViewModel;
import bg.softuni.servicesvratsa.repository.ServiceRepository;
import bg.softuni.servicesvratsa.service.PictureService;
import bg.softuni.servicesvratsa.service.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ModelMapper modelMapper, PictureService pictureService) {
        this.serviceRepository = serviceRepository;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
    }


    @Override
    public void initServices() {

        if (serviceRepository.count() != 0) {
            return;
        }

        ServiceEntity service = new ServiceEntity();

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    service.setId(1L);
                    service.setName("Смяна на Водомер");
                    service.setServiceId(UUID.randomUUID().toString());
                    service.setDescription("Професионална услуга за смяна на водомер с нов, " +
                            "висококачествен модел. Включва изваждане на стария водомер, инсталиране на новия, " +
                            "и тестване за коректна работа. " +
                            "Гарантирано качество и прецизност в измерванията.");
                    service.setPrice(BigDecimal.valueOf(20.60));
                    service.setPictureId(1L);
                    break;

                case 1:
                    service.setId(2L);
                    service.setName("Ремонт и Смяна на Кранове");
                    service.setServiceId(UUID.randomUUID().toString());
                    service.setDescription("Извършваме ремонт и смяна на всички видове кранове в дома или бизнес сграда. " +
                            "Независимо дали става въпрос за течове, неправилна работа или просто обновяване, " +
                            "нашите техници осигуряват бърза и ефективна услуга.");
                    service.setPrice(BigDecimal.valueOf(25.30));
                    service.setPictureId(2L);
                    break;

                case 2:
                    service.setId(3L);
                    service.setName("Инсталиране на Санитарни Прибори");
                    service.setServiceId(UUID.randomUUID().toString());
                    service.setDescription("Предлагаме услуги за инсталиране на санитарни прибори като тоалетни, " +
                            "умивалници, душ кабини и други. Нашият екип от квалифицирани техници гарантира " +
                            "коректна инсталация и функционалност на всеки санитарен елемент. " +
                            "Цената варира в зависимост от обема на работа.");
                    service.setPrice(BigDecimal.valueOf(50.35));
                    service.setPictureId(3L);
                    break;

                case 3:
                    service.setId(4L);
                    service.setName("Обновяване на Водопроводната Система");
                    service.setServiceId(UUID.randomUUID().toString());
                    service.setDescription("Планираме и извършваме обновяване на водопроводната система в жилищни, " +
                            "търговски или индустриални обекти. Това включва замяна на стари тръби, " +
                            "инсталация на нови кранове и оборудване за подобряване на ефективността и " +
                            "надеждността на системата. Цената варира в зависимост от обема на работа.");
                    service.setPrice(BigDecimal.valueOf(90.60));
                    service.setPictureId(4L);
                    break;
            }

            serviceRepository.save(service);

        }
    }

    @Override
    public void addNewService(Long pictureId, ServicesServiceModel servicesServiceModel) {


        ServiceEntity service = modelMapper.map(
                servicesServiceModel, ServiceEntity.class);

        service.setPictureId(pictureId);

        serviceRepository.save(service);
    }

    @Override
    public List<ServicesServiceModel> findAllServices() {
        return serviceRepository
                .findAll()
                .stream()
                .map(serviceEntity -> modelMapper.map(serviceEntity, ServicesServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceViewModel findServiceById(Long id) {
        ServiceEntity serviceEntity = serviceRepository.findById(id).orElse(null);//TODO THROW EXCEPTION

        ServiceViewModel serviceViewModel = modelMapper.map(serviceEntity, ServiceViewModel.class);

        if (serviceEntity != null) {
            PictureEntity picture = pictureService.findPictureById(serviceEntity.getPictureId());

            serviceViewModel.setPictureTitle(picture.getTitle());
            serviceViewModel.setPictureUrl(picture.getUrl());
        }

        return serviceViewModel;

    }
}
