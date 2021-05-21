package io.swagger.configuration;

import io.swagger.model.User;
import io.swagger.model.UserRole;
import io.swagger.model.UserStatus;
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

    public Applicationrunner(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users =
                Arrays.asList(
                        new User("test1", "test1", "testname1", "test1@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, UserStatus.Active),
                        new User("test2", "test2", "testname2", "test2@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, UserStatus.Active),
                        new User("test3", "test3", "testname3", "test3@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, UserStatus.Active)
                        );

        users.forEach(userRepository::save);
    }
}