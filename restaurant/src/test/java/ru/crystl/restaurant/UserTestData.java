package ru.crystl.restaurant;

import ru.crystl.restaurant.model.Role;
import ru.crystl.restaurant.model.User;

import java.util.Collections;
import java.util.Date;

import static ru.crystl.restaurant.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class, "registered", "meals", "password");

    public static final int ADMIN_ID = START_SEQ + 1;

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", new Date(), Collections.singleton(Role.USER));
    }

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN/*, Role.USER*/);


}