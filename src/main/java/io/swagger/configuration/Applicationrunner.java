package io.swagger.configuration;

import io.swagger.model.RegisterDTO;
import io.swagger.model.User;
import io.swagger.model.UserRole;
import io.swagger.model.Status;
import io.swagger.repository.UserRepository;
import io.swagger.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "bankingAPI.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Applicationrunner implements ApplicationRunner {

    UserRepository userRepository;

    UserService userService;

    public Applicationrunner(UserRepository userRepository, UserService userService) {
      this.userRepository = userRepository;
      this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users =
                Arrays.asList(
                        new User("test1", "test1", "testname1", "test1@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, Status.Active),
                        new User("test2", "test2", "testname2", "test2@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, Status.Active),
                        new User("test3", "test3", "testname3", "test3@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, Status.Active)
                        );

        users.forEach(userRepository::save);

        RegisterDTO customer = new RegisterDTO("customer", "hoi", "customer hoi", "customer@bankapi.com");
        RegisterDTO employee = new RegisterDTO("employee", "hoi", "employee hoi", "employee@bankapi.com", UserRole.ROLE_Employee);
        RegisterDTO inactiveUser = new RegisterDTO("inactive", "hoi", "inactive hoi", "inactive@bankapi.com");
        inactiveUser.setUserStatus(Status.Inactive);

        userService.createUser(customer);
        userService.createUser(employee);
        userService.createUser(inactiveUser);
    }
}