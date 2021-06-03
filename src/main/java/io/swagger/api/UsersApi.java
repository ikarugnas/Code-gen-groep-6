/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import io.swagger.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-03T11:13:57.329Z[GMT]")
@Validated
public interface UsersApi {

    @Operation(summary = "Delete a user from the system by using their id.", description = "Remove a user from the system.", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "users", "employee" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "User has been removed successfully."),
        
        @ApiResponse(responseCode = "400", description = "Bad credentials. Could not find user."),
        
        @ApiResponse(responseCode = "401", description = "Bad Authentication."),
        
        @ApiResponse(responseCode = "404", description = "An unexpected error has occurred. Please contact support.") })
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "User to delete.", required=true, schema=@Schema()) @PathVariable("id") Long id);


    @Operation(summary = "get user by id", description = "Searches in the system for a user value with Id as the Key.", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "users", "employee" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "User found.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        
        @ApiResponse(responseCode = "400", description = "Id was invalid."),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized."),
        
        @ApiResponse(responseCode = "404", description = "User not found.") })
    @RequestMapping(value = "/users/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<User>> getUserById(@Parameter(in = ParameterIn.PATH, description = "The id of the user to get.", required=true, schema=@Schema()) @PathVariable("id") Long id);


    @Operation(summary = "get user by username", description = "Searches in the system for a user value with username as the Key.", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "users", "employee" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "User found.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        
        @ApiResponse(responseCode = "400", description = "invalid input, object invalid."),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized."),
        
        @ApiResponse(responseCode = "404", description = "An unexpected error has occurred. Please contact support if this persists.") })
    @RequestMapping(value = "/users/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<User>> getUserByUsername(@Parameter(in = ParameterIn.PATH, description = "The id of the user to get.", required=true, schema=@Schema()) @PathVariable("username") String username);


    @Operation(summary = "gets all users", description = "Shows all the users that are existing on the system.", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "users", "employee" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Users found and returned.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized."),
        
        @ApiResponse(responseCode = "403", description = "Query parameter invalid."),
        
        @ApiResponse(responseCode = "404", description = "No users found.") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset, @Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit, @Parameter(in = ParameterIn.QUERY, description = "the username that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "username", required = false) String username, @Parameter(in = ParameterIn.QUERY, description = "the name that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name, @Parameter(in = ParameterIn.QUERY, description = "the email that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "email", required = false) String email, @Parameter(in = ParameterIn.QUERY, description = "the role that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "role", required = false) String role, @Parameter(in = ParameterIn.QUERY, description = "the user status that it should search on" ,schema=@Schema()) @Valid @RequestParam(value = "userStatus", required = false) String userStatus);


    @Operation(summary = "Log in the user.", description = "Validate credentials of the user and then log them in.", tags={ "users", "customer", "employee" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Validated and logged in successfully. Welcome!"),
        
        @ApiResponse(responseCode = "400", description = "Bad credentials. Could not validate user."),
        
        @ApiResponse(responseCode = "404", description = "An unexpected error has occurred. Please contact support.") })
    @RequestMapping(value = "/users/login",
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> loginUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody LoginDTO body);


    @Operation(summary = "Register a user to the system.", description = "Register a user to the system.", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "users", "employee" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "user registered."),
        
        @ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized."),
        
        @ApiResponse(responseCode = "409", description = "A user with that login or email already exists.") })
    @RequestMapping(value = "/users",
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> registerUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody RegisterDTO body);


    @Operation(summary = "Update a user", description = "Update the values of a user with the information that is given.", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "users", "employee" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The changes have successfully been executed."),
        
        @ApiResponse(responseCode = "400", description = "User not found."),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized."),
        
        @ApiResponse(responseCode = "404", description = "An error has occurred.") })
    @RequestMapping(value = "/users/{id}",
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.PATH, description = "The id of the user to update.", required=true, schema=@Schema()) @PathVariable("id") Long id, @Parameter(in = ParameterIn.DEFAULT, description = "The updated user data.", required=true, schema=@Schema()) @Valid @RequestBody User body);

}

