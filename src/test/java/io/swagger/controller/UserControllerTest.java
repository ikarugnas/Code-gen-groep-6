package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import io.swagger.model.User;
import io.swagger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

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

        registerDTO = new RegisterDTO("customer", "Ab3de^gh", "customer hoi", "customer@bankapi.com");
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
    public void whenRegisterUserWithoutBeingLoggedInShouldReturnForbidden() throws Exception {
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "Customer", password = "customer1", roles = "Customer")
    public void whenRegisterUserWithRoleCustomerShouldReturnForbidden() throws Exception {
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void whenRegisterUserWithEmptyBodyShouldReturnBadRequestAndErrorMessage() throws Exception {
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Username, password, name and email are empty"));
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void whenRegisterUserWithUsernameThatIsAlreadyTakenShouldReturnUnprocessableEntityAndErrorString() throws Exception {
        given(userService.usernameAlreadyExist(registerDTO.getUsername())).willReturn(true);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").value("Username already exits"));
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void whenRegisterUserWithInvalidUsernameShouldReturnUnprocessableEntityAndErrorString() throws Exception {
        registerDTO.setEmail("test.bankingApi.com");

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").value("Email address is invalid"));
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void whenRegisterUserWithInvalidPasswordShouldReturnUnprocessableEntityAndErrorString() throws Exception {
        registerDTO.setPassword("test1");

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").value("Password length must be 8 or more, password misses a capital letter and password misses one of these special characters [!, @, #, $, %, ^, & or *]"));
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void whenRegisterUserWithValidInputShouldReturnCreatedAndNewUser() throws Exception {
        User user = new User(registerDTO.getUsername(),
                registerDTO.getPassword(),
                registerDTO.getName(),
                registerDTO.getEmail(),
                registerDTO.getRole(),
                registerDTO.getDayLimit(),
                registerDTO.getTransactionLimit(),
                registerDTO.getUserStatus());

        given(userService.createUser(registerDTO)).willReturn(user);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isEmpty())
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.role[0]").value(user.getRoles().get(0).toString()))
                .andExpect(jsonPath("$.dayLimit").value(user.getDayLimit()))
                .andExpect(jsonPath("$.transactionLimit").value(user.getTransactionLimit()))
                .andExpect(jsonPath("$.userStatus").value(user.getUserStatus().toString()));
    }

}
