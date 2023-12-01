package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.entity.ContactEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.service.ContactServiceModel;
import bg.softuni.servicesvratsa.repository.ContactRepository;
import bg.softuni.servicesvratsa.service.ContactService;
import bg.softuni.servicesvratsa.service.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    private ContactService serviceToTest;

    @Mock
    private ContactRepository mockContactRepository;

    @Mock
    private UserService mockUserService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private Authentication mockAuthentication;

    @BeforeEach
    void setUp() {
        serviceToTest = new ContactServiceImpl(mockContactRepository, mockUserService, mockModelMapper);
    }

    @Test
    void testSaveMessage() {
        String username = "pesho";
        UserEntity user = new UserEntity();
        user.setUsername(username);

        ContactServiceModel contactServiceModel = createContactServiceModel();
        ContactEntity contactEntity = createContactEntity(contactServiceModel);

        SecurityContextHolder.getContext().setAuthentication(mockAuthentication);

        when(mockUserService.findByUsername(username))
                .thenReturn(user);

        when(mockAuthentication.getName())
                .thenReturn(username);

        when(mockModelMapper.map(contactServiceModel, ContactEntity.class))
                .thenReturn(contactEntity);

        serviceToTest.saveMessage(contactServiceModel);

        verify(mockContactRepository, times(1)).save(contactEntity);

    }

    private ContactServiceModel createContactServiceModel() {
        ContactServiceModel contactServiceModel = new ContactServiceModel();
        contactServiceModel.setFirstName("Gosho");
        contactServiceModel.setLastName("Goshev");
        contactServiceModel.setMessage("test test bg alert");
        contactServiceModel.setEmail("pesho@test.bg");
        contactServiceModel.setAnswered(true);
        contactServiceModel.setPhoneNumber("0888888888");

        return contactServiceModel;
    }

    private ContactEntity createContactEntity(ContactServiceModel contactServiceModel) {
        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setFirstName(contactServiceModel.getFirstName());
        contactEntity.setLastName(contactServiceModel.getLastName());
        contactEntity.setMessage(contactServiceModel.getMessage());
        contactEntity.setEmail(contactServiceModel.getEmail());
        contactEntity.setAnswered(contactServiceModel.isAnswered());
        contactEntity.setPhoneNumber(contactServiceModel.getPhoneNumber());

        return contactEntity;
    }
}
