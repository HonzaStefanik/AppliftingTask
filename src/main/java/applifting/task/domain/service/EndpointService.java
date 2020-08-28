package applifting.task.domain.service;

import applifting.task.domain.exception.EntityNotFoundException;
import applifting.task.infrastructure.model.MonitoredEndpoint;
import applifting.task.infrastructure.model.MonitoringResult;
import applifting.task.infrastructure.repository.MonitoredEndpointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EndpointService {

    private final MonitoredEndpointRepository monitoredEndpointRepository;

    public EndpointService(MonitoredEndpointRepository monitoredEndpointRepository) {
        this.monitoredEndpointRepository = monitoredEndpointRepository;
    }

    public List<MonitoringResult> getRecentResults(int endpointId, int count) throws EntityNotFoundException {
        return monitoredEndpointRepository.findById(endpointId)
                .map(endpoint -> endpoint.getMonitoringResults().subList(0, count))
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<MonitoringResult> getRecentResults(int endpointId) throws EntityNotFoundException {
        return getRecentResults(endpointId, 10);
    }

    public MonitoredEndpoint getEndpoint(int endpointId) throws EntityNotFoundException {
        return monitoredEndpointRepository.findById(endpointId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void persist(MonitoredEndpoint monitoredEndpoint){
        monitoredEndpointRepository.save(monitoredEndpoint);
    }
}
