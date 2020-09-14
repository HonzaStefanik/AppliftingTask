package applifting.task.domain;

import applifting.task.domain.service.EndpointService;
import applifting.task.domain.service.UserEndpointService;
import applifting.task.domain.service.UserService;
import applifting.task.infrastructure.model.MonitoredEndpoint;
import applifting.task.infrastructure.model.User;
import applifting.task.testsupport.BaseIntegrationTest;
import applifting.task.testsupport.TestEntities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEndpointServiceTestIT extends BaseIntegrationTest {

    @Autowired
    UserEndpointService userEndpointService;

    @Autowired
    UserService userService;

    @Autowired
    EndpointService endpointService;

    @Test
    @DisplayName("Persist monitored endpoint")
    public void persistMonitoredEndpoint() {
        User firstUser = userService.persist(TestEntities.getUser());
        User secondUser = userService.persist(TestEntities.getUser());
        MonitoredEndpoint firstEndpoint = endpointService.persist(
                TestEntities.getMonitoredEndpoint(
                        "firstTestEndpoint",
                        "www.test.cz",
                        firstUser
                )
        );
        MonitoredEndpoint secondEndpoint = endpointService.persist(
                TestEntities.getMonitoredEndpoint(
                        "secondEndpoint",
                        "www.test.com",
                        secondUser
                )
        );

        List<MonitoredEndpoint> result = userEndpointService.getMonitoredEndpoints(firstUser.getId());

        assertNotNull(result, "Monitored endpoints should not be null");
        assertFalse(result.isEmpty(), "Monitored endpoints should not be empty");
        assertEquals(1, result.size(), "Monitored endpoints should be size of 1");
        assertEquals(firstUser.getId(), result.iterator().next().getUser().getId(), "Users should match");

        userService.deleteById(firstUser.getId());
        userService.deleteById(secondUser.getId());
    }

}
