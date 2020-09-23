package ru.crystl.restaurant.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.crystl.restaurant.TimingExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.crystl.restaurant.util.ValidationUtil.getRootCause;

public abstract class AbstractRepositoryTest {
    @SpringJUnitConfig(locations = {
            "classpath:spring/spring-app.xml",
            "classpath:spring/spring-db.xml"
    })


    @Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
    @ExtendWith(TimingExtension.class)
    abstract public class AbstractServiceTest {

        public <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> rootExceptionClass) {
            assertThrows(rootExceptionClass, () -> {
                try {
                    runnable.run();
                } catch (Exception e) {
                    throw getRootCause(e);
                }
            });
        }
    }
}
