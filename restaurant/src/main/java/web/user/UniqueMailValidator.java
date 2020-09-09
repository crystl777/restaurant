package web.user;

import model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import repository.user.DataJpaUserRepository;

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
