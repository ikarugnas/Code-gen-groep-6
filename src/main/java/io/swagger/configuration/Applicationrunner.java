package io.swagger.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "bankingAPI.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Applicationrunner implements ApplicationRunner {

//    UserRepository userRepository;


//    public Applicationrunner(UserRepository userRepository) {
//      this.userRepository = userRepository
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        List<User> users =
//                Arrays.asList(
//                        new User(),
//                        new User(),
//                        new User()
//                        );
//
//        users.forEach(userRepository::save);
    }
}
