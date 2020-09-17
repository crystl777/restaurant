package ru.crystl.restaurant.web.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.crystl.restaurant.model.User;
import ru.crystl.restaurant.repository.user.DataJpaUserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

    private final DataJpaUserRepository repository;

    public UserService(DataJpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
