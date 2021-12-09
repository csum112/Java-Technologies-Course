package ro.uaic.info.lab7.services;

import lombok.RequiredArgsConstructor;
import ro.uaic.info.lab7.repositories.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Stateless
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserService {
    private final UserRepository userRepository;
}
