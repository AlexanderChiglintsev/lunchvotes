package ru.snx.lunchvotes.repository;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractTest {

    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
    protected <T extends Throwable> void checkValidation(Runnable runnable) {
        assertThrows(ConstraintViolationException.class, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                Throwable rootCause = NestedExceptionUtils.getRootCause(e);
                throw rootCause != null ? rootCause : e;
            }
        });
    }
}
