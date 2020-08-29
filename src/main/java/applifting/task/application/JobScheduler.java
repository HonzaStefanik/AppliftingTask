package applifting.task.application;

import applifting.task.domain.service.MonitoringService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        value = "jobs.endpoint-update.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class JobScheduler {

    private final MonitoringService monitoringService;

    public JobScheduler(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Scheduled(cron = "${jobs.endpoint-update.cron}")
    public void updateEndpoints(){
        monitoringService.updateMonitoredEndpointStatus();
    }
}
