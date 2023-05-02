package etsii.tfg.DungeonRaiders.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.player.Character;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import etsii.tfg.DungeonRaiders.validation.BasicInfo;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final String REGISTER_USER_VIEW = "users/registerUserForm";
    private static final String REGISTER_USER_URL = "/register";
    private static final String LOGIN_USER_VIEW = "users/loginUserForm";
    private static final String LOGIN_USER_URL = "/login";
    private static final String UPDATE_USER_URL = "/update";
    private static final String UPDATE_USER_VIEW = "users/updateUserForm";
    private static final String STATS_USER_URL = "/stats";
    private static final String STATS_USER_VIEW = "users/stats";

    private UserService userService;
    private PlayerService playerService;
    private StatService statService;

    @Autowired
    public UserController(UserService userService, PlayerService playerService, StatService statService) {
        this.userService = userService;
        this.playerService = playerService;
        this.statService = statService;
    }

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
            user.setDecryptedPassword(user.getPassword());
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
    public String updateUserPostForm(ModelMap modelMap, @Validated(BasicInfo.class) User user,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword, BindingResult result,
            @AuthenticationPrincipal CustomUserDetails loggedUser) {

        User authenticatedUser = userService.authenticatedUser();

        Boolean passwordChangeWanted = !oldPassword.isEmpty() && !newPassword.isEmpty();
        Boolean incorrectOldPassword = passwordChangeWanted
                && userService.checkIncorrectOldPassword(oldPassword, authenticatedUser);
        Boolean incorrectNewPassword = passwordChangeWanted && newPassword.length() < 5;

        Boolean newUsernameIsRepeated = !authenticatedUser.getUsername().equals(user.getUsername())
                && userService.findUserByUsername(user.getUsername()) != null;

        Game activeUserGame = playerService.activeUserGame();
        Boolean userInGame = activeUserGame != null;

        if (result.hasErrors() || incorrectOldPassword || incorrectNewPassword || newUsernameIsRepeated || userInGame) {
            List<String> messages = new ArrayList<String>();
            if (incorrectOldPassword) {
                messages.add("La contraseña antigua no coincide");
            }
            if (newUsernameIsRepeated) {
                messages.add("El nuevo nombre de usuario ya esta siendo utilizado por otra persona");
            }
            if (userInGame) {
                messages.add("No puedes modificar tu usuario estando en partida");
            }
            if (incorrectNewPassword) {
                messages.add("La contraseña debe tener un tamaño superior a 5 caracteres");
            }
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("messages", messages);
            return UPDATE_USER_VIEW;
        } else {
            user.setId(authenticatedUser.getId());
            if (passwordChangeWanted) {
                user.setDecryptedPassword(newPassword);
            }
            loggedUser.setUsername(user.getUsername());
            userService.save(user);
            return "redirect:/";
        }

    }

    @GetMapping(STATS_USER_URL)
    public String showStatsUser(ModelMap modelMap) {
        Integer userId = userService.authenticatedUser().getId();

        for (Character characterPlayer : Character.values()) {
            modelMap.addAttribute("games" + characterPlayer.getName(),
                    statService.gamesPlayedWithCaracterByUserId(userId, characterPlayer));
        }
        modelMap.addAttribute("totalCoins", statService.totalCoinsByUserId(userId));
        modelMap.addAttribute("totalWounds", statService.totalWoundsByUserId(userId));
        modelMap.addAttribute("gamesMaxCoins", statService.gamesMaxCoinsByUserId(userId));
        modelMap.addAttribute("gamesMaxWounds", statService.gamesMaxWoundsUserId(userId));
        modelMap.addAttribute("gamesWon", statService.gamesWonByUserId(userId));
        modelMap.addAttribute("gamesPlayed", statService.gamesPlayedByUserId(userId));
        return STATS_USER_VIEW;
    }

}
