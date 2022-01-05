package ro.uaic.info.services;

import lombok.RequiredArgsConstructor;
import ro.uaic.info.entities.User;
import ro.uaic.info.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.findById(username).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", username)));
    }

    private User createUser(String username, String password) {
        final User user = new User();
        user.setUsername(username);
        userRepository.save(user);
        return user;
    }

    public void ensureUserExists(String username) {
        if (userRepository.findById(username).isEmpty()) {
            throw new EntityNotFoundException(String.format("User with id %s not found", username));
        }
    }
}
