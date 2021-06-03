package io.swagger.service;

import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import io.swagger.model.Status;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import io.swagger.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    public String loginUser(LoginDTO loginDTO) throws Exception {
        try {
            //Check userStatus
            User user = userRepository.findByUsername(loginDTO.getUsername());
            if (user.getUserStatus().equals(Status.Inactive)){
                throw new Exception("User account is inactive, please take contact with an employee!");
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            return jwtTokenProvider.createToken(loginDTO.getUsername(), user.getRoles());
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username or Password is incorrect");
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }

    public boolean usernameAlreadyExist(String username) {
        return (userRepository.findByUsername(username) != null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findUserById(id);
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
    
    public User updateUser(UUID idOfUserToUpdate, User updatedUser) {
        System.out.println("step1");
        User userToUpdate = userRepository.findUserById(idOfUserToUpdate);
        //Commented line under may be used if Id needs to be changed.
        //userToUpdate.setId(updatedUser.getId());
        userToUpdate.setUsername(updatedUser.getUsername());
        userToUpdate.setPassword(updatedUser.getPassword());
        userToUpdate.setName(updatedUser.getName());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setRoles(updatedUser.getRoles());
        //Commented line under may be used once accounts are functional.
        //userToUpdate.setAccount(updatedUser.getAccount());
        userToUpdate.setDayLimit(updatedUser.getDayLimit());
        userToUpdate.setTransactionLimit(updatedUser.getTransactionLimit());
        userToUpdate.setUserStatus(updatedUser.getUserStatus());
        userRepository.save(userToUpdate);
        System.out.println("step2");
        return userToUpdate;
    }

    //First implementation to delete a user from the system without firing a query.
    public void deleteUser(UUID uuid) {
        int index = 0;
        for (User u : userRepository.findAll()) {

            if (u.getId() == uuid) {
                userRepository.delete(u);
                return;
            }
            index++;
        }
    }

    public User getuserByInput(String searchString){
        return userRepository.getUserByInput(searchString);
    }
}
