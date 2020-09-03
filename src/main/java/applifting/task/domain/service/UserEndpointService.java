package applifting.task.domain.service;

import applifting.task.domain.exception.EntityNotFoundException;
import applifting.task.infrastructure.model.MonitoredEndpoint;
import applifting.task.infrastructure.model.User;
import applifting.task.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserEndpointService {

    private final UserService userService;

    public UserEndpointService(UserService userService) {
        this.userService = userService;
    }

    public void persistMonitoredEndoint(int userId, MonitoredEndpoint monitoredEndpoint) {
        userService.findById(userId)
                .ifPresentOrElse(
                        user -> {
                            user.addEndpoint(monitoredEndpoint);
                            monitoredEndpoint.setUser(user);
                            userService.persist(user);
                        },
                        () -> log.info("Failed to persist endpoint for user with id {}", userId));
    }

    public List<MonitoredEndpoint> getMonitoredEndpoints(int userId) {
        return userService.findById(userId)
                .map(User::getMonitoredEndpoints)
                .orElse(Collections.emptyList());
    }

}
