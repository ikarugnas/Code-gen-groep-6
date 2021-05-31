package io.swagger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import io.swagger.model.UserRole;
import io.swagger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private LoginDTO loginDTO;
    private RegisterDTO registerDTO;
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp(){
        mapper = new ObjectMapper();

        loginDTO = new LoginDTO();
        loginDTO.setUsername("test");
        loginDTO.setPassword("test1");

        registerDTO = new RegisterDTO("customer", "hoi", "customer hoi", "customer@bankapi.com");


    }

    // Login tests
    @Test
    public void whenLoginUserWithEmptyBodyShouldReturnBadRequestAndErrorMessage() throws Exception {
        this.mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Username and password are empty"));
    }

    @Test
    public void whenLoginUserShouldReturnOkAndJWTString() throws Exception {
        String bearerToken = "Bearer 34324234235235";
        given(userService.loginUser(loginDTO)).willReturn(bearerToken);

        this.mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(bearerToken));
    }

    @Test
    public void whenLoginUserWithInvalidInputShouldReturnUnprocessableEntityAndErrorString() throws Exception {
        ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username or Password is incorrect");
        given(userService.loginUser(loginDTO)).willThrow(responseStatusException);

        this.mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(loginDTO)))
                .andExpect(status().is(responseStatusException.getRawStatusCode()))
                .andExpect(jsonPath("$").value(responseStatusException.getReason()));
    }

    @Test
    public void whenLoginUserHasStatusInactiveShouldReturnUnprocessableEntityAndErrorString() throws Exception {
        Exception exception = new Exception("User account is inactive, please take contact with an employee!");
        given(userService.loginUser(loginDTO)).willThrow(exception);

        this.mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(loginDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").value(exception.getMessage()));
    }

    // Register tests
    @Test
    public void whenRegisterUserWithEmptyBodyShouldReturnBadRequestAndErrorMessage() throws Exception {


        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Username, password, name and email are empty"));
    }

    @Test
    public void whenRegisterUserWithUsernameThatIsAlreadyTakenShouldReturnUnprocessableEntityAndErrorString() throws Exception {
        given(userService.usernameAlreadyExist(registerDTO.getUsername())).willReturn(true);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Username already exits"));
    }

    @Test
    public void whenRegisterUserWithInvalidUsernameShouldReturnUnprocessableEntityAndErrorString() throws Exception {
        registerDTO.setEmail("test.bankingApi.com");

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").value("Email address is invalid"));
    }

//    @Test
//    public void when

}
