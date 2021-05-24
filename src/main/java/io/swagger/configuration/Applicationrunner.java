package io.swagger.configuration;

import io.swagger.model.RegisterDTO;
import io.swagger.model.User;
import io.swagger.model.UserRole;
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

    UserService userService;


    public Applicationrunner(UserService userService) {
      this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RegisterDTO customer = new RegisterDTO("customer", "hoi", "customer hoi", "customer@bankapi.com");
        RegisterDTO employee = new RegisterDTO("employee", "hoi", "employee hoi", "employee@bankapi.com", UserRole.ROLE_Employee);

        userService.createUser(customer);
        userService.createUser(employee);
    }
}
