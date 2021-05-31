package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDTOTest {

    private RegisterDTO registerDTO;

    @BeforeEach
    public void setUp(){
        registerDTO = new RegisterDTO();
    }

    @Test
    public void createRegisterDTOShouldNotBeNull() {
        assertNotNull(registerDTO);
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenPropertiesAreEmpty(){
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Username, password, name and email are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenUsernameIsEmpty(){
        registerDTO.setPassword("password");
        registerDTO.setName("name");
        registerDTO.setEmail("email");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Username is empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenPasswordIsEmpty(){
        registerDTO.setUsername("username");
        registerDTO.setName("name");
        registerDTO.setEmail("email");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Password is empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenNameIsEmpty(){
        registerDTO.setUsername("username");
        registerDTO.setPassword("password");
        registerDTO.setEmail("email");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Name is empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenEmailIsEmpty(){
        registerDTO.setUsername("username");
        registerDTO.setPassword("password");
        registerDTO.setName("name");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Email is empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenUsernameAndPasswordAreEmpty(){
        registerDTO.setName("name");
        registerDTO.setEmail("email");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Username and password are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenPasswordAndNameAreEmpty(){
        registerDTO.setUsername("username");
        registerDTO.setEmail("email");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Password and name are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenNameAndEmailAreEmpty(){
        registerDTO.setUsername("username");
        registerDTO.setPassword("password");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Name and email are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenUsernameAndEmailAreEmpty(){
        registerDTO.setPassword("password");
        registerDTO.setName("name");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Username and email are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenOnlyUsernameIsNotEmpty(){
        registerDTO.setUsername("username");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Password, name and email are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenOnlyPasswordIsNotEmpty(){
        registerDTO.setPassword("password");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Username, name and email are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenOnlyNameIsNotEmpty(){
        registerDTO.setName("name");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Username, password and email are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveStringWhenOnlyEmailIsNotEmpty(){
        registerDTO.setEmail("email");
        assertEquals(registerDTO.getNullOrEmptyProperties(), "Username, password and name are empty");
    }

    @Test
    public void getNullOrEmptyPropertiesShouldGiveNullWhenNoPropertiesAreEmpty(){
        RegisterDTO registerDTO = new RegisterDTO("customer", "hoi", "customer hoi", "customer@bankapi.com");
        assertNull(registerDTO.getNullOrEmptyProperties());
    }

}