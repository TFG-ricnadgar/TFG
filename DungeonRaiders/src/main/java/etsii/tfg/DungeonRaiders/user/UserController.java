package etsii.tfg.DungeonRaiders.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final String REGISTER_USER_VIEW = "users/registerUserForm";
    private static final String REGISTER_USER_URL = "/register";
    private static final String LOGIN_USER_VIEW = "users/loginUserForm";
    private static final String LOGIN_USER_URL = "/login";
    private static final String UPDATE_USER_URL = "/update";
    private static final String UPDATE_USER_VIEW = "users/updateUserForm";

    @Autowired
    private UserService userService;

    @GetMapping(value = REGISTER_USER_URL)
    public String registerGetForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return REGISTER_USER_VIEW;
    }

    @PostMapping(value = REGISTER_USER_URL)
    public String registerPostForm(ModelMap modelMap, @Valid User user, BindingResult result,
            @RequestParam(value = "repeatedPassword") String repeatedPassword) {
        Boolean differentPasswords = !user.getPassword().equals(repeatedPassword);
        Boolean usernameIsRepeated = userService.findUserByUsername(user.getUsername()) != null;
        if (result.hasErrors() || differentPasswords || usernameIsRepeated) {
            List<String> messages = new ArrayList<String>();
            if (differentPasswords) {
                messages.add("Las contraseñas no coinciden");
            }
            if (usernameIsRepeated) {
                messages.add("Ya hay alguien con este nombre de usuario");
            }
            modelMap.addAttribute("messages", messages);
            modelMap.addAttribute("user", user);
            return REGISTER_USER_VIEW;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.save(user);
            return "redirect:/user" + LOGIN_USER_URL;

        }
    }

    @GetMapping(value = LOGIN_USER_URL)
    public String loginGetForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return LOGIN_USER_VIEW;
    }

    @GetMapping(value = UPDATE_USER_URL)
    public String updateUserGetForm(ModelMap modelMap) {
        modelMap.addAttribute("user", userService.authenticatedUser());
        return UPDATE_USER_VIEW;
    }

    @PostMapping(value = UPDATE_USER_URL)
    public String updateUserPostForm(ModelMap modelMap, @Valid User user,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword, BindingResult result) {

        User authenticatedUser = userService.authenticatedUser();

        Boolean incorrectOldPassword = userService.checkPasswordChange(oldPassword, newPassword, user);

        Boolean newUsernameIsRepeated = !authenticatedUser.getUsername().equals(user.getUsername())
                && userService.findUserByUsername(user.getUsername()) != null;

        if (result.hasErrors() || incorrectOldPassword || newUsernameIsRepeated) {
            List<String> messages = new ArrayList<String>();
            if (incorrectOldPassword) {
                messages.add("La contraseña antigua no coincide");
            }
            if (newUsernameIsRepeated) {
                messages.add("El nuevo nombre de usuario ya esta siendo utilizado por otra persona");
            }
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("messages", messages);
            return UPDATE_USER_VIEW;
        }
        user.setId(authenticatedUser.getId());
        userService.save(user);
        return "redirect:/";

    }

}
