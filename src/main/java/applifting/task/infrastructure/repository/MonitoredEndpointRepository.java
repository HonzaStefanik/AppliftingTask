package applifting.task.infrastructure.repository;

import applifting.task.infrastructure.model.MonitoredEndpoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonitoredEndpointRepository extends CrudRepository<MonitoredEndpoint, Integer> {

    public Optional<MonitoredEndpoint> findMostRecentResultsById(int id);

}
