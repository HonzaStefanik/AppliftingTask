package applifting.task.handler.controller;

import applifting.task.domain.exception.EntityNotFoundException;
import applifting.task.domain.service.EndpointService;
import applifting.task.handler.dto.MonitoringResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/result")
public class ResultController {

    private final EndpointService endpointService;

    public ResultController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    // TODO authorization / authentication (assuming only user who monitors the endpoint should see the result - check the assignment later)
    @GetMapping(value = "/{endpointId}")
    public List<MonitoringResultDTO> getResultsForEndpoint(@PathVariable Integer endpointId) throws EntityNotFoundException {
        return endpointService.getRecentResults(endpointId)
                .stream()
                .map(MonitoringResultDTO::fromMonitoringResult)
                .collect(Collectors.toList());
    }
}
