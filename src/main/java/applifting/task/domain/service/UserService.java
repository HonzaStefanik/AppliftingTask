package applifting.task.domain.service;

import applifting.task.infrastructure.model.User;
import applifting.task.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public void persist(User user) {
        userRepository.save(user);
    }
}