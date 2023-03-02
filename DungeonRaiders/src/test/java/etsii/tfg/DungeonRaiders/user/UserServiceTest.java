package etsii.tfg.DungeonRaiders.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceTest {

    @Autowired
    private UserService userService;

    private User userTest;

    @BeforeEach
    void createInitialUser() {
        userTest = new User();
        userTest.setUsername("userTest");
        userTest.setDecryptedPassword("userTest");
        userService.save(userTest);
    }

    @Test
    void testFindUserByUsername() {
        User user = userService.findUserByUsername(userTest.getUsername());
        assertEquals(user.getId(), userTest.getId());
    }

    @Test
    void testSave() {
        User oldUser = userService.findUserByUsername(userTest.getUsername());
        String oldPassword = oldUser.getPassword();
        oldUser.setDecryptedPassword("differentPassword");
        userService.save(oldUser);
        String newPassword = userService.findUserByUsername(oldUser.getUsername()).getPassword();
        assertNotEquals(oldPassword, newPassword);
    }

    @Test
    @WithMockUser(username = "userTest")
    void testAuthenticatedUser() {
        User user = userService.authenticatedUser();
        assertEquals(user.getId(), userTest.getId());
    }

    @Test
    @WithMockUser(username = "userTest")
    void testAuthenticatedUsername() {
        assertEquals(userService.authenticatedUsername(), "userTest");
    }

    @Test
    void testCheckIncorrectOldPasswordPositive() {
        assertFalse(userService.checkIncorrectOldPassword("userTest", userTest));
    }

    @Test
    void testCheckIncorrectOldPasswordNegative() {
        assertTrue(userService.checkIncorrectOldPassword("userTestWrongPassword", userTest));
    }

}
