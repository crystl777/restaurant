package ru.crystl.restaurant;

import org.springframework.test.context.ActiveProfilesResolver;

public class ActiveProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{Profiles.REPOSITORY_IMPLEMENTATION, Profiles.getActiveDbProfile()};
    }
}
