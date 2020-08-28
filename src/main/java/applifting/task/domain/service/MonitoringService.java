package applifting.task.domain.service;

import applifting.task.infrastructure.model.MonitoringResult;
import applifting.task.infrastructure.model.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class MonitoringService {

    private final RestTemplate restTemplate;
    private final UserService userService;
    private final EndpointService endpointService;

    public MonitoringService(RestTemplateBuilder restTemplateBuilder, UserService userService, EndpointService endpointService) {
        this.restTemplate = restTemplateBuilder.build();
        this.userService = userService;
        this.endpointService = endpointService;
    }

    public void updateMonitoredEndpointStatus() {
        userService.findAllUsers()
                .forEach(this::updateEndpoints);
    }

    public void updateMonitoredEndpointStatus(int userId) {
        userService.findById(userId)
                .ifPresent(this::updateEndpoints);
    }

    private void updateEndpoints(User user) {
        user.getMonitoredEndpoints()
                .forEach(endpoint -> {
                            ResponseEntity<String> response = restTemplate.getForEntity(endpoint.getUrl(), String.class);
                            LocalDateTime now = LocalDateTime.now();
                            MonitoringResult result = createMonitoringResult(response, now);
                            endpoint.getMonitoringResults().add(result);
                            endpoint.setLastUpdatedDate(now);
                            endpointService.persist(endpoint);
                        }
                );
    }

    private MonitoringResult createMonitoringResult(ResponseEntity<String> responseEntity, LocalDateTime time) {
        return new MonitoringResult(
                time,
                responseEntity.getStatusCode().value(),
                responseEntity.getBody()
        );
    }
}
