package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.service.ContactServiceModel;
import bg.softuni.servicesvratsa.model.view.ContactViewModel;

import java.util.List;

public interface ContactService {
    void saveMessage(ContactServiceModel contactServiceModel);

    List<ContactViewModel> getAllUnreadMessages();

    Integer getAllUnreadMessagesCount();

    List<ContactViewModel> getAllAnsweredMessages();

    void markMessageAsReadById(Long id);
}
