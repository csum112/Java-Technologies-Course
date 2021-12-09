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

    public User getOrCreate(Long userID) {
        return userRepository.findById(userID).orElse(createUser(userID));
    }

    private User createUser(Long userID) {
        final User user = new User();
        user.setId(userID);
        userRepository.save(user);
        return user;
    }

    public void ensureUserExists(Long userID) {
        if (userRepository.findById(userID).isEmpty()) {
            throw new EntityNotFoundException(String.format("User with id %d not found", userID));
        }
    }
}
