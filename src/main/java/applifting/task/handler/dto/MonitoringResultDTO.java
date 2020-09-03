package applifting.task.handler.dto;

import applifting.task.infrastructure.model.MonitoringResult;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MonitoringResultDTO {

    private LocalDateTime dateOfCheck;
    private Integer returnStatus;
    private String returnPayload;

    public MonitoringResultDTO(LocalDateTime dateOfCheck, Integer returnStatus, String returnPayload) {
        this.dateOfCheck = dateOfCheck;
        this.returnStatus = returnStatus;
        this.returnPayload = returnPayload;
    }

    public static MonitoringResultDTO fromMonitoringResult(MonitoringResult monitoringResult) {
        return new MonitoringResultDTO(
                monitoringResult.getDateOfCheck(),
                monitoringResult.getReturnStatus(),
                monitoringResult.getReturnPayload()
        );
    }
}
