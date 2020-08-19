package applifting.task.infrastructure.repository;

import applifting.task.infrastructure.model.MonitoredEndpoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoredEndpointRepository extends CrudRepository<MonitoredEndpoint, Integer> {
}
