package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.models.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.models.binding.UserRegistrationBindingModel;
import bg.softuni.mobilelele.models.service.UserRegistrationServiceModel;
import bg.softuni.mobilelele.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthController {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    public AuthController(UserServiceImpl userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginBindingModel userLoginBindingModel) {
        boolean loginSuccessful = userService.
                login(userLoginBindingModel);

        LOGGER.info("User tried to login. User with name {} tried to login. Success = {}?",
                userLoginBindingModel.getUsername(),
                loginSuccessful);

        if (loginSuccessful) {
            return "redirect:/";
        }

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerUser() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(
            @Valid UserRegistrationBindingModel userModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userModel.getPassword().equals(userModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegistrationServiceModel serviceModel =
                modelMapper.map(userModel, UserRegistrationServiceModel.class);

        if (!userService.isUserNameFree(serviceModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("userNameOccupied", true);

            return "redirect:/users/register";
        } else {
            userService.registerAndLoginUser(serviceModel);
        }

        return "redirect:/";
    }
}
