package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDTOTest {

    private LoginDTO loginDTO;

    @BeforeEach
    public void setUp(){
        loginDTO = new LoginDTO();
    }

    @Test
    public void createLoginDTOShouldNotBeNull() {
        assertNotNull(loginDTO);
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenPropertiesAreEmpty(){
        assertEquals(loginDTO.getNullOrEmptyProperties(), "Username and password are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenUsernameIsEmpty(){
        loginDTO.setPassword("password");
        assertEquals(loginDTO.getNullOrEmptyProperties(), "Username is empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenPasswordIsEmpty(){
        loginDTO.setUsername("username");
        assertEquals(loginDTO.getNullOrEmptyProperties(), "Password is empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveNullWhenNoPropertiesAreEmpty(){
        loginDTO.setUsername("username");
        loginDTO.setPassword("password");

        assertNull(loginDTO.getNullOrEmptyProperties());
    }
}
