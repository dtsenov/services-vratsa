package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.binding.UserRegisterBindingModel;
import bg.softuni.servicesvratsa.model.service.UserServiceModel;
import bg.softuni.servicesvratsa.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private boolean mismatchPasswords;
    private boolean usernameExist;
    private boolean emailExist;

    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        mismatchPasswords = false;
        usernameExist = false;
        emailExist = false;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("mismatchPasswords", mismatchPasswords);
        model.addAttribute("usernameExist", usernameExist);
        model.addAttribute("emailExist", emailExist);

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        boolean samePasswords = userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword());

        boolean isUsernameExist = userService.checkUsername(userRegisterBindingModel.getUsername());
        boolean isEmailExist = userService.checkEmail(userRegisterBindingModel.getEmail());

        if (bindingResult.hasErrors() || !samePasswords || isUsernameExist || isEmailExist ) {



            if (isUsernameExist) {
                usernameExist = true;
            }

            if (isEmailExist) {
                emailExist = true;
            }

            if (!samePasswords) {
                mismatchPasswords = true;
            }

            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";

        }

        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/users/login";
    }
}
