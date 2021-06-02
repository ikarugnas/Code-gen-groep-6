package io.swagger.api;

import io.swagger.model.Status;
import io.swagger.model.User;
import io.swagger.model.UserRole;
import io.swagger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsersApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private User user;
    @BeforeEach
    public void init() {
        user = new User("testusername", "testpassword", "test", "test@gmail.com", UserRole.ROLE_Employee, 500.0, 100.0, Status.Active);
    }

//    @Test
//    public void callingEndpointUsersShouldReturnJson() throws Exception {
////        Iterable<User> users = Arrays.asList(user);
////        given(userService.getAllUsers())
////                .willReturn(Arrays.asList(user));
//
//        mvc.perform(get("/users"))
//                .andExpect(status().isOk());
//    }

//    @Test
//    void deleteUser() {
//    }
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
}