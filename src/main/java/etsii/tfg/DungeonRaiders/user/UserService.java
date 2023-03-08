package etsii.tfg.DungeonRaiders.user;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String authenticatedUsername() {
        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return currentUserName;
    }

    public User authenticatedUser() {
        User user = findUserByUsername(authenticatedUsername());
        return user;
    }

    public Boolean checkIncorrectOldPassword(String oldPassword, User authenticatedUser) {
        Boolean oldPasswordIsIncorrect = true;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(oldPassword, authenticatedUser.getPassword())) {
            oldPasswordIsIncorrect = false;
        }
        return oldPasswordIsIncorrect;
    }

}
