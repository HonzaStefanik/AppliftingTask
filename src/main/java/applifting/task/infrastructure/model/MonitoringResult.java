package applifting.task.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class MonitoringResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDateTime lastCheck;
    private Integer returnStatus;
    private String returnPayload;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(LocalDateTime lastCheck) {
        this.lastCheck = lastCheck;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnPayload() {
        return returnPayload;
    }

    public void setReturnPayload(String returnPayload) {
        this.returnPayload = returnPayload;
    }
}
