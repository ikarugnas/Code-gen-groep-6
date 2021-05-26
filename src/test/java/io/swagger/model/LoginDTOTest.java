package io.swagger.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDTOTest {

    @Test
    public void createLoginDTOShouldNotBeNull() {
        LoginDTO loginDTO = new LoginDTO();
        assertNotNull(loginDTO);
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenPropertiesAreEmpty(){
        LoginDTO loginDTO = new LoginDTO();
        assertEquals(loginDTO.getNullOrEmptyProperties(), "Username and password are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveNullWhenNoPropertiesAreEmpty(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("username");
        loginDTO.setPassword("password");

        assertEquals(loginDTO.getNullOrEmptyProperties(), null);
    }
}
