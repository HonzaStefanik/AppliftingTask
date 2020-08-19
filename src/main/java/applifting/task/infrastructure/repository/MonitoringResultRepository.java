package applifting.task.infrastructure.repository;

import applifting.task.infrastructure.model.MonitoringResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringResultRepository extends CrudRepository<MonitoringResult, Integer> {
}
