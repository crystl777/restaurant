package ru.crystl.restaurant.web.user;

import ru.crystl.restaurant.model.User;
import ru.crystl.restaurant.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = UserUtil.asTo(user);
    }

    public int getId() {
        return user.id();
    }

    public void update(User new) {
        user = new;
    }

    public User getUserTo() {
        return user;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
