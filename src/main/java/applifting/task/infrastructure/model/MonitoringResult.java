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
    private LocalDateTime dateOfCheck;
    private Integer returnStatus;
    private String returnPayload;

    public MonitoringResult() {
    }

    public MonitoringResult(LocalDateTime dateOfCheck, Integer returnStatus, String returnPayload){
        this.dateOfCheck = dateOfCheck;
        this.returnStatus = returnStatus;
        this.returnPayload = returnPayload;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateOfCheck() {
        return dateOfCheck;
    }

    public void setDateOfCheck(LocalDateTime lastCheck) {
        this.dateOfCheck = lastCheck;
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
