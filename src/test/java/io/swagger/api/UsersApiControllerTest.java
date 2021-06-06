package io.swagger.api;

import io.swagger.model.Status;
import io.swagger.model.User;
import io.swagger.model.UserRole;
import io.swagger.repository.UserRepository;
import io.swagger.service.MyUserDetailsService;
import io.swagger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UsersApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private User userEmployee;
    private User userCustomer;
    private List<User> users;

    @BeforeEach
    public void init() {
        userEmployee = new User("testusername", "testpassword", "test", "test@gmail.com", UserRole.ROLE_Employee, 500.0, 100.0, Status.Active);
        userCustomer = new User("testCustomer1", "testpassword", "Customer", "customer@gmail.com", UserRole.ROLE_Customer, 500.0, 100.0, Status.Active);
        userEmployee.setId(UUID.randomUUID());
        userCustomer.setId(UUID.randomUUID());
        //users.forEach(userRepository::save);
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void callingAllUsersShouldReturnOk() throws Exception {
        this.mvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void callingEndpointUsersAsEmployeeShouldReturnStatusOkAndArrayOfTypeUsers() throws Exception {
        given(userService.getAllUsers())
                .willReturn(Arrays.asList(userEmployee, userCustomer));

        mvc.perform(get("/users")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser(username = "Customer", password = "hoi", roles = "Customer")
    public void callingEndpointUsersAsCustomerShouldReturnArrayOfSize1() throws Exception {
        //given(myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)).willReturn(true);
        //given(userService.getUserByUsername(myUserDetailsService.getLoggedInUser().getUsername())).willReturn(userCustomer);
        given(userService.getAllUsers())
                .willReturn(Arrays.asList(userCustomer));

        mvc.perform(get("/users")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void callingDeactivateUserAsEmployeeShouldReturnOk() throws Exception {
        UUID customerUUID = userCustomer.getId();
//        mvc.perform(delete("/users/{uuid}").param("uuid", customerUUID.toString()))
        mvc.perform(delete("/users/id").param("id", customerUUID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
//    public void callingEndpointUsersAsEmployeeWithNegativeLimitThrowsException() throws Exception {
//        given(offset < 0).willReturn(false);
//
//        mvc.perform(get("/users")).andExpect(
//                status().isUnprocessableEntity());
//    }

}

//
//    @Test
//    void getUserById() {
//    }
//
//    @Test
//    void getUserByUsername() {
//    }
//
//    @Test
//    void getUsers() {
//    }
//
//    @Test
//    void updateUser() {
//    }

//    @Test
//    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
//    public void creatingUserShouldReturnStatusCreated() throws Exception {
//        mvc.perform(post("/users")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content("{}"))
//                .andExpect(status().isCreated());
//    }