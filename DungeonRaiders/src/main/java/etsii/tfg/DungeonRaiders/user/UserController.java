package etsii.tfg.DungeonRaiders.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final String REGISTER_USER_VIEW = "users/registerUserForm";
    private static final String REGISTER_USER_URL = "/register";
    private static final String LOGIN_USER_VIEW = "users/loginUserForm";
    private static final String LOGIN_USER_URL = "/login";
    private static final String LOGIN_ERROR_USER_URL = "/login-error";

    @Autowired
    private UserService userService;

    @GetMapping(value = REGISTER_USER_URL)
    public String registerGetForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return REGISTER_USER_VIEW;
    }

    @PostMapping(value = REGISTER_USER_URL)
    public String registerPostForm(ModelMap modelMap, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return REGISTER_USER_VIEW;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.save(user);
            return "redirect:" + LOGIN_USER_URL;
        }
    }

    @GetMapping(value = LOGIN_USER_URL)
    public String loginGetForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return LOGIN_USER_VIEW;
    }

}
