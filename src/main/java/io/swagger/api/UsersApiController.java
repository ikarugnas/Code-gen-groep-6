package io.swagger.api;

import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "User to delete.", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> getUserById(@Parameter(in = ParameterIn.PATH, description = "The id of the user to get.", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n}, {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> getUserByUsername(@Parameter(in = ParameterIn.PATH, description = "The id of the user to get.", required=true, schema=@Schema()) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n}, {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset,@Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit,@Parameter(in = ParameterIn.QUERY, description = "the username that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "username", required = false) String username,@Parameter(in = ParameterIn.QUERY, description = "the name that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name,@Parameter(in = ParameterIn.QUERY, description = "the email that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "email", required = false) String email,@Parameter(in = ParameterIn.QUERY, description = "the role that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "role", required = false) String role,@Parameter(in = ParameterIn.QUERY, description = "the user status that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "userStatus", required = false) String userStatus) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n}, {\n  \"password\" : \"hiIamapassword4$\",\n  \"role\" : \"Customer\",\n  \"dayLimit\" : 1.4658129805029452,\n  \"userStatus\" : \"Active\",\n  \"name\" : \"Bert Geersen\",\n  \"id\" : 1,\n  \"transactionLimit\" : 5.962133916683182,\n  \"email\" : \"BertGeersen1@gmail.com\",\n  \"account\" : {\n    \"owner\" : \"owner\",\n    \"balance\" : 0.8008281904610115,\n    \"absoluteLimit\" : 0,\n    \"iban\" : \"NL55 RABO 1234 5678 90\",\n    \"active\" : \"Active\",\n    \"type\" : \"Current\",\n    \"transaction\" : [ {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    }, {\n      \"transactionType\" : \"Transaction\",\n      \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n      \"amount\" : 6.027456183070403,\n      \"userPerforming\" : \"BG12345\",\n      \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n      \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n    } ]\n  },\n  \"username\" : \"BG12345\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> loginUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody LoginDTO body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity registerUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody RegisterDTO body) {
        String accept = request.getHeader("Accept");

        if (userService.usernameAlreadyExist(body.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exits");
        }

        User user = userService.createUser(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.PATH, description = "The id of the user to update.", required=true, schema=@Schema()) @PathVariable("id") Long id,@Parameter(in = ParameterIn.DEFAULT, description = "The updated user data.", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
