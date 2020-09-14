package applifting.task.handler.controller;

import applifting.task.domain.exception.EntityNotFoundException;
import applifting.task.domain.service.MonitoringService;
import applifting.task.domain.service.UserEndpointService;
import applifting.task.handler.dto.MonitoredEndpointDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/endpoint")
public class EndpointController {

    private final MonitoringService monitoringService;
    private final UserEndpointService userEndpointService;

    public EndpointController(MonitoringService monitoringService, UserEndpointService userEndpointService) {
        this.monitoringService = monitoringService;
        this.userEndpointService = userEndpointService;
    }


    @GetMapping("/{userId}")
    public List<MonitoredEndpointDTO> getMonitoredEndpoints(@PathVariable int userId) {
        return userEndpointService.getMonitoredEndpoints(userId)
                .stream()
                .map(MonitoredEndpointDTO::fromMonitoredEndpoint)
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}")
    public void addMonitoredEndpoint(
            @PathVariable int userId,
            @RequestBody MonitoredEndpointDTO monitoredEndpointDTO
    ) {
        userEndpointService.persistMonitoredEndoint(
                userId,
                MonitoredEndpointDTO.fromMonitoredEndpointDto(monitoredEndpointDTO)
        );
    }

    @DeleteMapping("/{endpointId}")
    public void deleteMonitoredEndpoint(@PathVariable int endpointId) {
        monitoringService.removeMonitoredEndpoint(endpointId);
    }

    @PostMapping("/monitoring/{endpointId}")
    public void updateMonitoredEndpoint(@PathVariable int endpointId) throws EntityNotFoundException {
        monitoringService.updateMonitoredEndpoint(endpointId);
    }


}
