package io.swagger.service;

import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import io.swagger.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService() {
    }

    public User createUser(RegisterDTO registerDTO){
        User user = new User(registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                registerDTO.getName(),
                registerDTO.getEmail(),
                registerDTO.getRole(),
                registerDTO.getDayLimit(),
                registerDTO.getTransactionLimit(),
                registerDTO.getUserStatus());

        userRepository.save(user);

        return userRepository.findByUsername(registerDTO.getUsername());
    }

    public String loginUser(LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            return jwtTokenProvider.createToken(loginDTO.getUsername(), userRepository.findByUsername(loginDTO.getUsername()).getRoles());
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username or Password is incorrect");
        }

    }

    public boolean usernameAlreadyExist(String username) {
        return (userRepository.findByUsername(username) != null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String usernameToSearchFor) {
        return userRepository.findUserByUsernameQuery(usernameToSearchFor);
    }

    public User getUserByName(String name){
        return userRepository.findUserByName(name);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
