package io.swagger.service;

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

    public User createUser(RegisterDTO userDTO){
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(), userDTO.getEmail(), userDTO.getRole(), userDTO.getDayLimit(), userDTO.getTransactionLimit(), userDTO.getUserStatus());

        userRepository.save(user);

        return userRepository.findUserByUsername(userDTO.getUsername());
    }
}
