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

    //region createRegisterDTOTests
    @Test
    public void createRegisterDTOShouldNotBeNull() {
        assertNotNull(registerDTO);
    }

    @Test
    public void createRegisterDTORoleShouldNotBeNull(){
        assertNotNull(registerDTO.getRole());
    }

    @Test
    public void createRegisterDTODayLimitShouldNotBeNull(){
        assertNotNull(registerDTO.getDayLimit());
    }

    @Test
    public void createRegisterDTOTransactionLimitShouldNotBeNull(){
        assertNotNull(registerDTO.getTransactionLimit());
    }

    @Test
    public void createRegisterDTOUserStatusShouldNotBeNull(){
        assertNotNull(registerDTO.getUserStatus());
    }
    //endregion

    //region getNullOrEmptyPropertiesTests
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
    //endregion

    //region hasValidEmail
    @Test
    public void hasValidEmailShouldReturnFalseWhenEmailHasNoAtSign(){
        registerDTO.setEmail("hoi.banking.nl");
        assertFalse(registerDTO.hasValidEmail());
    }

    //region prefix
    @Test
    public void hasValidEmailShouldReturnFalseWhenEmailPrefixStartsWithDot(){
        registerDTO.setEmail(".hoi@banking.nl");
        assertFalse(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnFalseWhenEmailPrefixEndsWithDot(){
        registerDTO.setEmail("hoi.@banking.nl");
        assertFalse(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnTrueWhenEmailPrefixContainsDot(){
        registerDTO.setEmail("hoi.hoi@banking.nl");
        assertTrue(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnTrueWhenEmailPrefixContainsUnderscore(){
        registerDTO.setEmail("hoi_hoi@banking.nl");
        assertTrue(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnTrueWhenEmailPrefixContainsHyphen(){
        registerDTO.setEmail("hoi-hoi@banking.nl");
        assertTrue(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnFalseWhenEmailPrefixContainsSomeThingElseThanDotUnderscoreOrHyphen(){
        registerDTO.setEmail("hoi#hoi@banking.nl");
        assertFalse(registerDTO.hasValidEmail());
    }
    //endregion

    //region domain
    @Test
    public void hasValidEmailShouldReturnFalseWhenEmailDomainEndsWithADotAndLessThanTwoLetters(){
        registerDTO.setEmail("hoi.test@banking.n");
        assertFalse(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnFalseWhenEmailDomainHasNoDot(){
        registerDTO.setEmail("hoi.test@banking");
        assertFalse(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnTrueWhenEmailDomainIsValid(){
        registerDTO.setEmail("hoi.test@banking.nl");
        assertTrue(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnTrueWhenEmailDomainHasAHyphen(){
        registerDTO.setEmail("hoi.test@bank-ing.nl");
        assertTrue(registerDTO.hasValidEmail());
    }

    @Test
    public void hasValidEmailShouldReturnFalseWhenEmailDomainHasNoLetterBeforeDot(){
        registerDTO.setEmail("hoi.test@.nl");
        assertFalse(registerDTO.hasValidEmail());
    }
    //endregion
    //endregion

    //region validatePassword
    @Test
    public void validatePasswordShouldGiveErrorString() {
        registerDTO.setPassword("");
        assertEquals(registerDTO.validatePassword(), "Password length must be 8 or more, password misses a letter, password misses a capital letter, password misses a number and password misses one of these special characters [!, @, #, $, %, ^, & or *]");
    }

    @Test
    public void validatePasswordShouldGiveErrorStringWhenLengthIsLessThan8() {
        registerDTO.setPassword("aA3$");
        assertEquals(registerDTO.validatePassword(), "Password length must be 8 or more");
    }

    @Test
    public void validatePasswordShouldGiveErrorStringWhenPasswordMissesALetter() {
        registerDTO.setPassword("AA3$ASDS");
        assertEquals(registerDTO.validatePassword(), "Password misses a letter");
    }

    @Test
    public void validatePasswordShouldGiveErrorStringWhenPasswordMissesACapitalLetter() {
        registerDTO.setPassword("aa3$asds");
        assertEquals(registerDTO.validatePassword(), "Password misses a capital letter");
    }

    @Test
    public void validatePasswordShouldGiveErrorStringWhenPasswordMissesANumber() {
        registerDTO.setPassword("Aad$asds");
        assertEquals(registerDTO.validatePassword(), "Password misses a number");
    }

    @Test
    public void validatePasswordShouldGiveErrorStringWhenPasswordMissesASpecChar() {
        registerDTO.setPassword("Aa2rasds");
        assertEquals(registerDTO.validatePassword(), "Password misses one of these special characters [!, @, #, $, %, ^, & or *]");
    }

    @Test
    public void validatePasswordShouldGiveAndInErrorStringWhenPasswordHas2Errors() {
        registerDTO.setPassword("Aa$asd");
        assertEquals(registerDTO.validatePassword(), "Password length must be 8 or more and password misses a number");
    }

    @Test
    public void validatePasswordShouldGiveAndAndACommaInErrorStringWhenPasswordHas3Errors() {
        registerDTO.setPassword("a$asd");
        assertEquals(registerDTO.validatePassword(), "Password length must be 8 or more, password misses a capital letter and password misses a number");
    }

    @Test
    public void validatePasswordShouldGiveAndAnd2CommasInErrorStringWhenPasswordHas4Errors() {
        registerDTO.setPassword("aasd");
        assertEquals(registerDTO.validatePassword(), "Password length must be 8 or more, password misses a capital letter, password misses a number and password misses one of these special characters [!, @, #, $, %, ^, & or *]");
    }
    //endregion

}