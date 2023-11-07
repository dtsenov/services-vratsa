package bg.softuni.vikuslugivratsa.web;

import bg.softuni.vikuslugivratsa.model.binding.UserRegisterBindingModel;
import bg.softuni.vikuslugivratsa.model.service.UserServiceModel;
import bg.softuni.vikuslugivratsa.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String registerConfirm(UserRegisterBindingModel userRegisterBindingModel) {

        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/login";
    }
}
