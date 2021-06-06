package io.swagger.api;

import io.swagger.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.MyUserDetailsService;
import io.swagger.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    @Autowired
    UserService userService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    //Sets the user to inactive
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<Void> deactivateUser(@Parameter(in = ParameterIn.PATH, description = "UUID of user to delete.", required=true, schema=@Schema()) @PathVariable("id") UUID id) {
            try {
                userService.deactivateUser(id);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (BadRequestException badRequestException) {
                log.error("Bad credentials. Could not find user.", badRequestException);
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            } catch (NotFoundException notFoundException) {
                log.error("An unexpected error has occurred. Please contact support.");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity<User> getUserById(@Parameter(in = ParameterIn.PATH, description = "The id of the user to get.", required=true, schema=@Schema()) @PathVariable("id") UUID id) {
            try {
                return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
            } catch (BadRequestException userIdInvalidException) {
                log.error("Id was invalid", userIdInvalidException);
                return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
            } catch (NotFoundException notFoundException) {
                log.error("User Not Found");
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity<User> getUserByUsername(@Parameter(in = ParameterIn.PATH, description = "The id of the user to get.", required=true, schema=@Schema()) @PathVariable("username") String username) {
            try {
                return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
            } catch (Exception usernameInvalidException) {
                log.error("An unexpected error has occurred. Please contact support if this persists.");
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity getUsers(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset, @Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit, @Parameter(in = ParameterIn.QUERY, description = "the username that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "username", required = false) String username, @Parameter(in = ParameterIn.QUERY, description = "the name that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name, @Parameter(in = ParameterIn.QUERY, description = "the email that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "email", required = false) String email, @Parameter(in = ParameterIn.QUERY, description = "the user status that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "userStatus", required = false) Status userStatus) {
        if (myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)) {
            User loggedInUser = userService.getUserByUsername(myUserDetailsService.getLoggedInUser().getUsername());
            List<User> customers = new ArrayList<User>();
            customers.add(loggedInUser);
            return new ResponseEntity<List<User>>(customers, HttpStatus.OK);
        }

        long defaultOffset = 0;
        long defaultLimit = 10;
        try {
            if(offset != null || limit != null || username != null || name != null || email != null || userStatus != null) {

                if(offset < 0) {
                    log.error("offset is negative");
                    return new ResponseEntity<List<User>>(HttpStatus.UNPROCESSABLE_ENTITY);
                }
                if(limit < 1) {
                    log.error("Cannot have limit under 1");
                    return new ResponseEntity<List<User>>(HttpStatus.UNPROCESSABLE_ENTITY);
                }

                if(offset == null) {
                    offset = defaultOffset;
                }
                if(limit == null) {
                    limit = defaultLimit;
                }

                if (username == null) {
                    username = "%";
                } else username = "%" + username + "%";

                if (name == null) {
                    name = "%";
                } else name = "%" + name + "%";

                if (email == null) {
                    email = "%";
                } else email = "%" + email + "%";

                if (userStatus == null) {
                    userStatus = userStatus.Active;
                }

                Pageable pageable = PageRequest.of(offset.intValue(), limit.intValue());
                return new ResponseEntity<Page<User>>(userService.findAllUsersWithFilter(pageable, username, name, email, userStatus), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
            }
        } catch (Exception e) {
                log.error("No results found.", e);
                return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity loginUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody LoginDTO body) {
        String accept = request.getHeader("Accept");

        String emptyProperty = body.getNullOrEmptyProperties();
        if (emptyProperty != null) {
            log.error("Tried logging in with empty propertie(s)");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyProperty);
        }

        try {
            log.info(body.getUsername() + " logged in succesfull");
            return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(body));
        } catch (ResponseStatusException responseStatusException) {
            log.error(body.getUsername() + "; " + responseStatusException.getReason(), responseStatusException);
            return ResponseEntity.status(responseStatusException.getStatus()).body(responseStatusException.getReason());
        } catch (Exception exception){
            log.error(body.getUsername() + " tried logging in with inactive user account", exception);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
        }
    }

    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity registerUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody RegisterDTO body) {
        String accept = request.getHeader("Accept");

        // Check if any property of the requestbody is empty or null
        String emptyProperty = body.getNullOrEmptyProperties();
        if (emptyProperty != null){
            log.error("Tried register user with empty properties");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyProperty);
        }

        // Check if username already exists
        if (userService.usernameAlreadyExist(body.getUsername())){
            log.error("Username already exists");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Username already exists");
        }

        // Check if email is valid
        if (!body.hasValidEmail()){
            log.error("Email is invalid");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Email address is invalid");
        }

        // Check if password meets requirements
        String passwordValidation = body.validatePassword();
        if (passwordValidation != null) {
            log.error("Password is invalid");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(passwordValidation);
        }

        User user = userService.createUser(body);

        log.info(user.getUsername() + " is created");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.PATH, description = "The id of the user to update.", required=true, schema=@Schema()) @PathVariable("id") UUID id, @Parameter(in = ParameterIn.DEFAULT, description = "The updated user data.", required=true, schema=@Schema()) @Valid @RequestBody RegisterDTO updatedUserData) {
            try {
                return new ResponseEntity<User>(userService.updateUser(id, updatedUserData), HttpStatus.OK);
            } catch (Exception e) {
                log.error("An error has occurred. Please contact support", e);
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
    }

}
