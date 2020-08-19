package applifting.task.infrastructure.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class MonitoredEndpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String url;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    @OneToMany(targetEntity = MonitoringResult.class)
    private List<MonitoringResult> monitoringResults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<MonitoringResult> getMonitoringResults() {
        return monitoringResults;
    }

    public void setMonitoringResults(List<MonitoringResult> monitoringResults) {
        this.monitoringResults = monitoringResults;
    }
}
