package io.swagger.model;

import io.swagger.model.LoginDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginDTOTest {

    @Test
    public void createLoginDTOShouldNotBeNull() {
        LoginDTO loginDTO = new LoginDTO();
        assertNotNull(loginDTO);
    }
}
