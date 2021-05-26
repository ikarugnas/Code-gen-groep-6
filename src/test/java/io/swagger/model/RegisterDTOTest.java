package io.swagger.model;

import io.swagger.model.RegisterDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegisterDTOTest {

    @Test
    public void createRegisterDTOShouldNotBeNull() {
        RegisterDTO registerDTO = new RegisterDTO();
        assertNotNull(registerDTO);
    }

}