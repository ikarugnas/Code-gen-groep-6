package io.swagger.service;

import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService() {
    }

    public User createUser(RegisterDTO registerDTO){
        User user = new User(registerDTO.getUsername(),
                registerDTO.getPassword(),
                registerDTO.getName(),
                registerDTO.getEmail(),
                registerDTO.getRole(),
                registerDTO.getDayLimit(),
                registerDTO.getTransactionLimit(),
                registerDTO.getUserStatus());

        userRepository.save(user);

        return userRepository.findUserByUsername(registerDTO.getUsername());
    }

    public boolean usernameAlreadyExist(String username) {
        return (userRepository.findUserByUsername(username) != null);
    }

    public boolean loginUser(LoginDTO loginDTO) {
        User user = userRepository.findUserByUsername(loginDTO.getUsername());

        return ((user != null) && (loginDTO.getPassword().equals(user.getPassword())));
    }
}
