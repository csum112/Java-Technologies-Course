package ro.uaic.info.lab7.config;

import lombok.extern.slf4j.Slf4j;
import ro.uaic.info.lab7.entities.User;
import ro.uaic.info.lab7.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Startup
@Singleton
public class PopulateDatabase {
    @Inject
    private UserRepository userRepository;
    @Inject
    private PasswordHash passwordHash;

    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("Running database initialization");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHash.initialize(parameters);

        final List<User> userList = new ArrayList<>();
        userList.add(new User("admin", passwordHash.generate("admin".toCharArray()), "admin", new ArrayList<>()));
        userList.forEach(userRepository::save);
    }
}
