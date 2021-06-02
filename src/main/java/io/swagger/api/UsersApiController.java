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
    public ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "UUID of user to delete.", required=true, schema=@Schema()) @PathVariable("uuid") UUID uuid) {
            try {
                //userService.deleteUser(uuid);
                userService.deactivateUser(uuid);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (BadRequestException badRequestException) {
//                log.error("No user with that UUID could be found.", userNotFoundException);
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
                //return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n}, {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (BadRequestException userIdInvalidException) {
                log.error("Id was invalid", userIdInvalidException);
                return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
            } catch (NotFoundException notFoundException) {
                log.error("User Not Found");
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity<User> getUserByUsername(@Parameter(in = ParameterIn.PATH, description = "The id of the user to get.", required=true, schema=@Schema()) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
                //return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n}, {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception usernameInvalidException) {
                log.error("Couldn't find username in the system", usernameInvalidException);
                return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
            }
        }
        log.error("User Not Found");
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    //@PreAuthorize("hasRole('Employee')")
    public ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset, @Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit, @Parameter(in = ParameterIn.QUERY, description = "the username that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "username", required = false) String username, @Parameter(in = ParameterIn.QUERY, description = "the name that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name, @Parameter(in = ParameterIn.QUERY, description = "the email that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "email", required = false) String email, @Parameter(in = ParameterIn.QUERY, description = "the role that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "role", required = false) UserRole role, @Parameter(in = ParameterIn.QUERY, description = "the user status that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "userStatus", required = false) Status userStatus) {
        long defaultOffset = 0;
        long defaultLimit = 10;
        try {
            if(offset == null) {
                offset = defaultOffset;
            }
            if(limit == null) {
                limit = defaultLimit;
            }

            if(username != null || name != null || email != null || role != null || userStatus != null) {

            if(username !=null) {
                User user = userService.getUserByUsername(username);
                return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
            }
                if(username != null){
                    if(name == null){
                        name = username;
                    }
                    if(email == null){
                        email = username;
                    }
                    if(role == null){
                        role = UserRole.valueOf(username);
                    }
                    if(userStatus == null){
                        userStatus = Status.valueOf(username);
                    }
                }

                if(name != null){
                    if(username == null){
                        username = name;
                    }
                    if(email == null){
                        email = name;
                    }
                    if(role == null){
                        role = UserRole.valueOf(name);
                    }
                    if(userStatus == null){
                        userStatus = Status.valueOf(name);
                    }
                }

            if(email != null) {
                if(username == null){
                    username = email;
                }
                if(name == null){
                    name = email;
                }
                if(role == null){
                    role = UserRole.valueOf(email);
                }
                if(userStatus == null){
                    userStatus = Status.valueOf(email);
                }
            }

            if(role != null) {
                if(username == null){
                    username = role.name();
                }
                if(name == null){
                    name = role.name();
                }
                if(email == null){
                    email = role.name();
                }
                if(userStatus == null){
                    userStatus = Status.valueOf(role.name());
                }
            }

            if(userStatus != null) {
                if(username == null){
                    username = userStatus.name();
                }
                if(name == null){
                    name = userStatus.name();
                }
                if(email == null){
                    email = userStatus.name();
                }
                if(role == null){
                    role = UserRole.valueOf(userStatus.name());
                }
            }

                return new ResponseEntity<List<User>>(userService.getUsers(offset, limit, username, name, email, role, userStatus), HttpStatus.OK);
                //return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n}, {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } else {
                return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
            }
        } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
            }
//        log.error("No users found.");
//        return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity loginUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody LoginDTO body) {
        String accept = request.getHeader("Accept");

        String emptyProperty = body.getNullOrEmptyProperties();
        if (emptyProperty != null) {
            log.error("Tried logging in with empty propertie(s)");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyProperty);
        }

        try {
            log.info(body.getUsername() + "logged in succesfull");
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
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Username already exits");
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
    public ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.PATH, description = "The id of the user to update.", required=true, schema=@Schema()) @PathVariable("id") UUID id, @Parameter(in = ParameterIn.DEFAULT, description = "The updated user data.", required=true, schema=@Schema()) @Valid @RequestBody User updatedUserData) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                System.out.println("test1");
                return new ResponseEntity<User>(userService.updateUser(id, updatedUserData), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
            }
        }
        log.error("Could not find user to update.");
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

}
