package applifting.task.domain.service;

import applifting.task.domain.exception.EntityNotFoundException;
import applifting.task.infrastructure.model.MonitoredEndpoint;
import applifting.task.infrastructure.model.User;
import applifting.task.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserEndpointService {

    private final UserService userService;
    private final EndpointService endpointService;

    public UserEndpointService(UserService userService, EndpointService endpointService) {
        this.userService = userService;
        this.endpointService = endpointService;
    }

    public void persistMonitoredEndoint(int userId, MonitoredEndpoint monitoredEndpoint) {
        userService.findById(userId)
                .ifPresentOrElse(
                        user -> {
                            user.addEndpoint(monitoredEndpoint);
                            userService.persist(user);
                        },
                        () -> log.info("Failed to persist endpoint for user with id {}", userId));
    }

    public void removeMonitoredEndpoint(int userId, int endpointId) throws EntityNotFoundException {
        MonitoredEndpoint monitoredEndpoint = endpointService.getEndpoint(endpointId);
        userService.findById(userId)
                .ifPresentOrElse(
                        user -> {
                            user.removeEndpoint(monitoredEndpoint);
                            userService.persist(user);
                        },
                        () -> log.info("Failed to remove endpoint for user with id {}", userId)
                );
    }

    public List<MonitoredEndpoint> getMonitoredEndpoints(int userId) throws EntityNotFoundException {
        return userService.findById(userId)
                .map(User::getMonitoredEndpoints)
                .orElseThrow(EntityNotFoundException::new);
    }

}
