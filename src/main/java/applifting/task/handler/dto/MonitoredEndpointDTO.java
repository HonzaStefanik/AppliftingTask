package applifting.task.handler.dto;

import applifting.task.infrastructure.model.MonitoredEndpoint;
import lombok.Generated;
import lombok.Getter;

import java.time.LocalDateTime;

public class MonitoredEndpointDTO {

    private Integer id;
    private String name;
    private String url;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;

    public MonitoredEndpointDTO(Integer id, String name, String url, LocalDateTime createdDate, LocalDateTime lastUpdatedDate) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public static MonitoredEndpointDTO fromMonitoredEndpoint(MonitoredEndpoint monitoredEndpoint) {
        return new MonitoredEndpointDTO(
                monitoredEndpoint.getId(),
                monitoredEndpoint.getName(),
                monitoredEndpoint.getUrl(),
                monitoredEndpoint.getCreatedDate(),
                monitoredEndpoint.getLastUpdatedDate()
        );
    }

    public static MonitoredEndpoint fromMonitoredEndpointDto(MonitoredEndpointDTO monitoredEndpointDTO) {
        return new MonitoredEndpoint(
                monitoredEndpointDTO.name,
                monitoredEndpointDTO.url,
                LocalDateTime.now()
        );
    }
}
