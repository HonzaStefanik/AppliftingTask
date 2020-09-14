package applifting.task.domain.service;

import applifting.task.domain.exception.EntityNotFoundException;
import applifting.task.infrastructure.model.MonitoredEndpoint;
import applifting.task.infrastructure.model.MonitoringResult;
import applifting.task.infrastructure.repository.MonitoredEndpointRepository;
import applifting.task.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class EndpointService {

    private final MonitoredEndpointRepository monitoredEndpointRepository;
    private final UserRepository userRepository;


    public EndpointService(MonitoredEndpointRepository monitoredEndpointRepository, UserRepository userRepository) {
        this.monitoredEndpointRepository = monitoredEndpointRepository;
        this.userRepository = userRepository;
    }

    public List<MonitoringResult> getRecentResults(int endpointId, int count) throws EntityNotFoundException {
        return monitoredEndpointRepository.findById(endpointId)
                .map(endpoint -> {
                    List<MonitoringResult> results = endpoint.getMonitoringResults();
                    if (results.isEmpty()) {
                        return Collections.<MonitoringResult>emptyList();
                    }
                    return results.subList(0, Math.min(results.size(), count));
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<MonitoringResult> getRecentResults(int endpointId) throws EntityNotFoundException {
        return getRecentResults(endpointId, 10);
    }

    public MonitoredEndpoint getEndpoint(int endpointId) throws EntityNotFoundException {
        return monitoredEndpointRepository.findById(endpointId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void delete(int endpointId) {
        Optional<MonitoredEndpoint> endpoint = monitoredEndpointRepository.findById(endpointId);
        endpoint.ifPresent(it -> {
            it.getUser().removeEndpoint(it);
            monitoredEndpointRepository.deleteById(endpointId);
        });
    }

    public MonitoredEndpoint persist(MonitoredEndpoint monitoredEndpoint) {
        return monitoredEndpointRepository.save(monitoredEndpoint);
    }
}
