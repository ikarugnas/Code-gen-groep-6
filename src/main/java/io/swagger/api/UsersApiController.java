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

    public ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset, @Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit, @Parameter(in = ParameterIn.QUERY, description = "the username that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "username", required = false) String username, @Parameter(in = ParameterIn.QUERY, description = "the name that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name, @Parameter(in = ParameterIn.QUERY, description = "the email that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "email", required = false) String email, @Parameter(in = ParameterIn.QUERY, description = "the role that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "role", required = false) String role, @Parameter(in = ParameterIn.QUERY, description = "the user status that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "userStatus", required = false) String userStatus) {
        String accept = request.getHeader("Accept");
        System.out.println("hi there");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
                //return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n}, {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
            }
        }
        log.error("No users found.");
        return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity loginUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody LoginDTO body) {
        String accept = request.getHeader("Accept");

        String emptyProperty = body.getNullOrEmptyProperties();
        if (emptyProperty != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyProperty);
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(body));
        } catch (ResponseStatusException responseStatusException) {
            return ResponseEntity.status(responseStatusException.getStatus()).body(responseStatusException.getReason());
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
        }
    }

    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity registerUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody RegisterDTO body) {
        String accept = request.getHeader("Accept");

        // Check if any property of the requestbody is empty or null
        String emptyProperty = body.getNullOrEmptyProperties();
        if (emptyProperty != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyProperty);
        }

        // Check if username already exists
        if (userService.usernameAlreadyExist(body.getUsername())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Username already exits");
        }

        // Check if email is valid
        if (!validEmail(body.getEmail())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Email address is invalid");
        }

        // Check if password meets requirements
        String passwordValidation = validatePassword(body.getPassword());
        if (passwordValidation != null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(passwordValidation);
        }

        User user = userService.createUser(body);

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

    private boolean validEmail(String email) {
        // Checks if email has an @
        if (!email.matches("(.+)(\\@)(.+)")) { return false; }

        String[] splitEmail = email.split("@");
        String prefix = splitEmail[0];
        String domain = splitEmail[1];

        // Checks if prefix is valid
        if (!(prefix.matches("[a-zA-Z0-9]+") || prefix.matches("([a-zA-Z0-9]+)([\\-\\.\\_])([a-zA-Z0-9]+)"))) { return false; }

        // Checks if domain is valid
        if (!(domain.matches("([a-zA-Z0-9]+)([\\.])([a-z]{2,})") || domain.matches("([a-zA-Z0-9]+)(\\-)([a-zA-Z0-9]+)([\\.])([a-z]{2,})"))) { return false; }

        return true;
    }

    private String validatePassword(String password) {

        // Check if password length is 8 or more
        if (password.length() < 8) {
            return "Password is invalid (password length must be 8 or more)";
        }

        // Check if password has a capital letter
        if (!(password.matches("(.*)([A-Z])(.*)"))) {
            return "Password is invalid (password misses a captital letter)!";
        }

        // Check if password has a number
        if (!(password.matches("(.*)([0-9])(.*)"))) {
            return "Password is invalid (password misses a number)!";
        }

        // Check if password has one of these special characters (!, @, #, $, %, ^, & or *)
        if (!(password.matches("(.*)([\\!\\@\\#\\$\\%\\^\\&\\*])(.*)"))) {
            return "Password is invalid (password misses one of these special characters [!, @, #, $, %, ^, & or *])!";
        }

        // returns null if password is valid
        return null;
    }

}
