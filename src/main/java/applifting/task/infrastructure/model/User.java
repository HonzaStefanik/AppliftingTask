package applifting.task.infrastructure.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private UUID accessToken;
    @OneToMany(targetEntity = MonitoredEndpoint.class)
    private List<MonitoredEndpoint> monitoredEndpoints;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(UUID access_Token) {
        this.accessToken = access_Token;
    }

    public List<MonitoredEndpoint> getMonitoredEndpoints() {
        return monitoredEndpoints;
    }

    public void setMonitoredEndpoints(List<MonitoredEndpoint> monitoredEndpoints) {
        this.monitoredEndpoints = monitoredEndpoints;
    }

    @Transient
    public void addEndpoint(MonitoredEndpoint monitoredEndpoint){
        this.monitoredEndpoints.add(monitoredEndpoint);
    }

    @Transient
    public void removeEndpoint(MonitoredEndpoint monitoredEndpoint){
        this.monitoredEndpoints.remove(monitoredEndpoint);
    }
}
