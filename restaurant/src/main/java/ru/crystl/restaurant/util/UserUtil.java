package ru.crystl.restaurant.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.crystl.restaurant.model.Role;
import ru.crystl.restaurant.model.User;

public class UserUtil {
    public static User createNewFromTo(User userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

//    public static User updateFromTo(User user, UserTo userTo) {
//        user.setName(userTo.getName());
//        user.setEmail(userTo.getEmail().toLowerCase());
//        user.setPassword(userTo.getPassword());
//        return user;
//    }
//
//    public static User asTo(User user) {
//        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
//    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
