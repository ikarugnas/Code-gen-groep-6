package io.swagger.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDTOTest {

    @Test
    public void createRegisterDTOShouldNotBeNull() {
        RegisterDTO registerDTO = new RegisterDTO();
        assertNotNull(registerDTO);
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenPropertiesAreEmpty(){
        RegisterDTO registerDTO = new RegisterDTO();
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Username, password, name and email are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveNullWhenNoPropertiesAreEmpty(){
        RegisterDTO registerDTO = new RegisterDTO("customer", "hoi", "customer hoi", "customer@bankapi.com");
        assertEquals(registerDTO.getNullOrEmptyProperties(), null);
    }

}