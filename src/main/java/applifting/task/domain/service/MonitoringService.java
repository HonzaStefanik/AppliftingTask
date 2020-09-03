package applifting.task.domain.service;

import applifting.task.domain.exception.EntityNotFoundException;
import applifting.task.infrastructure.model.MonitoredEndpoint;
import applifting.task.infrastructure.model.MonitoringResult;
import applifting.task.infrastructure.model.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;

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

    public void removeMonitoredEndpoint(int endpointId) {
        endpointService.delete(endpointId);
    }

    public void updateMonitoredEndpointStatus() {
        userService.findAllUsers()
                .forEach(this::updateEndpoints);
    }

    public void updateMonitoredEndpoint(int endpointId) throws EntityNotFoundException {
        updateEndpoint(endpointService.getEndpoint(endpointId));
    }

    private void updateEndpoints(User user) {
        user.getMonitoredEndpoints()
                .forEach(this::updateEndpoint);
    }

    private void updateEndpoint(MonitoredEndpoint endpoint) {
        MonitoringResult result;
        LocalDateTime now = LocalDateTime.now();
        if (isAbsoluteUrl(endpoint.getUrl())) {
            ResponseEntity<String> response = restTemplate.getForEntity(endpoint.getUrl(), String.class);
            result = createMonitoringResult(response, now);
        } else {
            result = createInvalidUrlResult(now);
        }
        endpoint.getMonitoringResults().add(result);
        endpoint.setLastUpdatedDate(now);
        endpointService.persist(endpoint);
    }

    private MonitoringResult createMonitoringResult(ResponseEntity<String> responseEntity, LocalDateTime time) {
        return new MonitoringResult(
                time,
                responseEntity.getStatusCode().value(),
                responseEntity.getBody()
        );
    }

    private MonitoringResult createInvalidUrlResult(LocalDateTime time) {
        return new MonitoringResult(
                time,
                404,
                null
        );
    }

    private boolean isAbsoluteUrl(String url) {
        try {
            return new URI(url).isAbsolute();
        } catch (URISyntaxException e) {
            return false;
        }

    }
}
