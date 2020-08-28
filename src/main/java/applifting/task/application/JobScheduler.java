package applifting.task.application;

import applifting.task.domain.service.MonitoringService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

    private final MonitoringService monitoringService;

    public JobScheduler(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Scheduled(cron = "${jobs.endpoint-update}")
    public void updateEndpoints(){
        monitoringService.updateMonitoredEndpointStatus();
    }
}
