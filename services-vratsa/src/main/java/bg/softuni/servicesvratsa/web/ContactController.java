package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.binding.ContactBindingModel;
import bg.softuni.servicesvratsa.service.ContactService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    private final ContactService contactService;
    private final ModelMapper modelMapper;

    public ContactController(ContactService contactService, ModelMapper modelMapper) {
        this.contactService = contactService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @PostMapping
    public String contactConfirm(@Valid ContactBindingModel contactBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("contactBindingModel", contactBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.contactBindingModel", bindingResult);

            return "redirect:contacts";

        }

        contactService.saveMessage(modelMapper.map(contactBindingModel, ContactService.class));

        return "contact-confirm";
    }
}
