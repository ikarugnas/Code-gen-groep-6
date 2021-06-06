package io.swagger.service;

import io.swagger.api.BadRequestException;
import io.swagger.api.NotFoundException;
import io.swagger.model.*;
import io.swagger.repository.UserRepository;
import io.swagger.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            //Check if user with username exists
            if (!usernameAlreadyExist(loginDTO.getUsername())){
                throw new Exception("Username or Password is incorrect");
            }

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

    public Page<User> findAllUsersWithFilter(Pageable pageable, String username, String name, String email, Status status) {
        return userRepository.findAllUsersWithFiltering(pageable, username, name, email, status);
    }

    public User getUserById(UUID id) throws BadRequestException, NotFoundException {
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

    public User getUserByRole(UserRole role) {
        return userRepository.findUserByRoles(role);
    }

    public User getUserByStatus(Status status) {
        return userRepository.findUserByUserStatus(status);
    }
    
    public User updateUser(UUID idOfUserToUpdate, RegisterDTO updatedUser) {
        User userToUpdate = userRepository.findUserById(idOfUserToUpdate);
        userToUpdate.setUsername(updatedUser.getUsername());
        userToUpdate.setPassword(updatedUser.getPassword());
        userToUpdate.setName(updatedUser.getName());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setDayLimit(updatedUser.getDayLimit());
        userToUpdate.setTransactionLimit(updatedUser.getTransactionLimit());
        userToUpdate.setUserStatus(updatedUser.getUserStatus());
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    //First implementation to delete a user from the system without firing a query.
//    public void deleteUser(UUID uuid) {
//        int index = 0;
//        for (User u : userRepository.findAll()) {
//
//            if (u.getId() == uuid) {
//                userRepository.delete(u);
//                return;
//            }
//            index++;
//        }
//    }

    //No separate endpoint to activate user. To reactivate the account use the updateUser method.
    public void deactivateUser(UUID uuid) throws BadRequestException, NotFoundException {
        User user = userRepository.findUserById(uuid);
        user.setUserStatus(Status.Inactive);
        userRepository.save(user);
    }
}