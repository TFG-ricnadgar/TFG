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

    public Boolean checkPasswordChange(String oldPassword, String newPassword, User user) {
        Boolean passwordChangeWanted = !oldPassword.isEmpty() && !newPassword.isEmpty();
        Boolean passwordChangeError = false;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordChangeWanted) {
            if (passwordEncoder.matches(oldPassword, authenticatedUser().getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
            } else {
                passwordChangeError = true;
            }
        }
        return passwordChangeError;
    }

}
