package com.dimensiondata.hibernate;

import com.dimensiondata.hibernate.entity.Server;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

public class HibernateServerServiceTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldValidateNullId() throws Exception {

        Server server = new Server();
        server.setName("test");
        Set<ConstraintViolation<Server>> constraintViolations =
                validator.validate(server);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Server> violation = constraintViolations.iterator().next();
        assertEquals("may not be null", violation.getMessage());
        assertEquals("id", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldValidateSizeId() throws Exception {

        Server server = new Server();
        server.setName("test");
        server.setId(RandomStringUtils.random(129));
        Set<ConstraintViolation<Server>> constraintViolations =
                validator.validate(server);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Server> violation = constraintViolations.iterator().next();
        assertEquals("size must be between 1 and 128", violation.getMessage());
        assertEquals("id", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldValidateNullName() throws Exception {

        Server server = new Server();
        server.setId("test");
        Set<ConstraintViolation<Server>> constraintViolations =
                validator.validate(server);

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Server> violation = constraintViolations.iterator().next();
        assertEquals("may not be null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldValidateSizeName() throws Exception {

        Server server = new Server();
        server.setId("test");
        server.setName(RandomStringUtils.random(256));
        Set<ConstraintViolation<Server>> constraintViolations =
                validator.validate(server);

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Server> violation = constraintViolations.iterator().next();
        assertEquals("size must be between 1 and 255", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }
}
