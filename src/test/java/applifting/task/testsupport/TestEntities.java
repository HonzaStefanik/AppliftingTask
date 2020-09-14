package applifting.task.testsupport;

import applifting.task.infrastructure.model.MonitoredEndpoint;
import applifting.task.infrastructure.model.User;

import java.time.LocalDateTime;
import java.util.Collections;

public class TestEntities {

    public static User getUser() {
        return new User(
                "testName",
                "test@email.com",
                "test-token",
                Collections.emptyList()
        );
    }

    public static MonitoredEndpoint getMonitoredEndpoint(String name, String url, User user) {
        return new MonitoredEndpoint(
                name,
                url,
                LocalDateTime.now(),
                null,
                user,
                Collections.emptyList()
        );
    }
}
