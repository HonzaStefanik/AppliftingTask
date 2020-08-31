package applifting.task.infrastructure.repository;

import applifting.task.infrastructure.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findUserByAccessToken(String accessToken);
}
