package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.exception.ContactNotFoundException;
import bg.softuni.servicesvratsa.model.entity.ContactEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.service.ContactServiceModel;
import bg.softuni.servicesvratsa.model.view.ContactViewModel;
import bg.softuni.servicesvratsa.repository.ContactRepository;
import bg.softuni.servicesvratsa.service.ContactService;
import bg.softuni.servicesvratsa.service.UserService;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ContactServiceImpl(ContactRepository contactRepository, UserService userService, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveMessage(ContactServiceModel contactServiceModel) {

        ContactEntity contactEntity = modelMapper.map(contactServiceModel, ContactEntity.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findByUsername(authentication.getName());

        contactEntity.setUser(user);

        contactRepository.save(contactEntity);
    }

    @Override
    public List<ContactViewModel> getAllUnreadMessages() {
        List<ContactEntity> allNotAnswered = contactRepository.getAllByAnsweredIsFalse();
        List<ContactViewModel> contactViewModelList = new ArrayList<>();

        allNotAnswered
                .forEach(contactEntity -> {
                    ContactViewModel mapped = modelMapper.map(contactEntity, ContactViewModel.class);
                    contactViewModelList.add(mapped);
                });

        return contactViewModelList;
    }

    @Override
    public Integer getAllUnreadMessagesCount() {
        List<ContactEntity> allNotAnswered = contactRepository.getAllByAnsweredIsFalse();
        return allNotAnswered.size();
    }

    @Override
    public List<ContactViewModel> getAllAnsweredMessages() {

        List<ContactEntity> allAnswered = contactRepository.getAllByAnsweredIsTrue();

        List<ContactViewModel> contactViewModelList = new ArrayList<>();

        allAnswered
                .forEach(contactEntity -> {
                    ContactViewModel mapped = modelMapper.map(contactEntity, ContactViewModel.class);
                    contactViewModelList.add(mapped);
                });

        return contactViewModelList;
    }

    @Override
    public void markMessageAsReadById(Long id) {
        ContactEntity contactEntity = contactRepository.findById(id)
                .orElse(null);

        if (contactEntity == null) {
            throw new ContactNotFoundException("Съобщението, което се опитвате да достъпите не е намерено.");
        } else {
            contactEntity.setAnswered(true);
        }

        contactRepository.save(contactEntity);
    }
}
