package ru.crystl.restaurant.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.crystl.restaurant.model.Role;
import ru.crystl.restaurant.model.User;

public class UserUtil {
    public static User createNew(User user) {
        return new User(null, user.getName(), user.getEmail().toLowerCase(), user.getPassword(), Role.USER);
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
