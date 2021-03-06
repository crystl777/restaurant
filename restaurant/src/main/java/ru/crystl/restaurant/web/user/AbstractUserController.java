package ru.crystl.restaurant.web.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.crystl.restaurant.model.User;
import ru.crystl.restaurant.repository.user.DataJpaUserRepository;
import ru.crystl.restaurant.to.UserTo;
import ru.crystl.restaurant.util.UserUtil;

import java.util.List;

import static ru.crystl.restaurant.util.UserUtil.prepareToSave;
import static ru.crystl.restaurant.util.ValidationUtil.*;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataJpaUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    @Cacheable("users")
    public List<User> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User create(UserTo userTo) {
        log.info("create from to {}", userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        Assert.notNull(user, "user must not be null");
        prepareToSave(user, passwordEncoder);
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        Assert.notNull(user, "user must not be null");
        assureIdConsistent(user, id);
        prepareToSave(user, passwordEncoder);
        repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        Assert.notNull(userTo, "user must not be null");
        assureIdConsistent(userTo, id);
        User user = get(userTo.id());
        User updatedUser = UserUtil.updateFromTo(user, userTo);
        prepareToSave(updatedUser, passwordEncoder);
//        repository.save(updatedUser); // !! need only for JDBC implementation
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }
}