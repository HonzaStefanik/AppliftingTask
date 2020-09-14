package applifting.task.domain;

import applifting.task.domain.service.UserService;
import applifting.task.infrastructure.model.User;
import applifting.task.testsupport.BaseIntegrationTest;
import applifting.task.testsupport.TestEntities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestIT extends BaseIntegrationTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Find user by valid ID")
    public void persistUser_findByValidId_expectUserReturned() {
        User user = userService.persist(TestEntities.getUser());

        User returnedUser = userService.findById(user.getId()).orElse(null);

        verifyUser(user, returnedUser);
        userService.deleteById(user.getId());
    }

    @Test
    @DisplayName("Find user by invalid ID")
    public void persistUser_findByInvalidId_expectNoResult() {
        User user = TestEntities.getUser();

        userService.persist(user);
        User returnedUser = userService.findById(100).orElse(null);

        assertNull(returnedUser, "Expected user to be null.");
        userService.deleteById(user.getId());
    }

    @Test
    @DisplayName("Find user by valid access token")
    public void persistUser_findByValidAccessToken_expectUserReturned() {
        User user = TestEntities.getUser();

        userService.persist(user);
        User returnedUser = userService.findByAccessToken(user.getAccessToken()).get();

        verifyUser(user, returnedUser);
        userService.deleteById(user.getId());
    }

    private void verifyUser(User expected, User actual) {
        assertAll(()-> {
            assertNotNull(actual, "Expected the user to not be null");
            assertEquals(expected.getId(), actual.getId(), "Expected IDs to match");
            assertEquals(expected.getEmail(), actual.getEmail(), "Expected emails to match");
            assertEquals(expected.getAccessToken(), actual.getAccessToken(), "Expected access tokens to match");
        });
    }
}
