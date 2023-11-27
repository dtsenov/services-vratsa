package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.ContactEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.repository.ContactRepository;
import bg.softuni.servicesvratsa.service.ContactService;
import bg.softuni.servicesvratsa.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public ContactService saveMessage(ContactService contactService) {

        ContactEntity contactEntity = modelMapper.map(contactService, ContactEntity.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findByUsername(authentication.getName());

        contactEntity.setUser(user);
        contactEntity.setAnswered(false);

        contactRepository.save(contactEntity);

        return null;
    }
}
