package io.swagger.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void createUserNoArgsShouldBeNull() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void idShouldNotBeNullPriorToJPAGeneration() {
        User user = new User("testusername", "testpassword", "test", "test@gmail.com", UserRole.ROLE_Employee, 500.0, 100.0, Status.Active);
        assertNull(user.getId());
    }
}