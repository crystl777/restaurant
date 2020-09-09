package ru.crystl.restaurant.web.user;

import ru.crystl.restaurant.HasIdAndEmail;
import ru.crystl.restaurant.repository.user.DataJpaUserRepository;
import ru.crystl.restaurant.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {

    private final DataJpaUserRepository repository;

    public UniqueMailValidator(DataJpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return HasIdAndEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasIdAndEmail user = ((HasIdAndEmail) target);
        User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
        if (dbUser != null && !dbUser.getId().equals(user.getId())) {
            errors.rejectValue("email", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
        }
    }
}
