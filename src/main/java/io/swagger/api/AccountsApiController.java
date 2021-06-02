package io.swagger.api;

import io.swagger.model.*;
import io.swagger.model.AccountWithTransactions;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.AccountService;
import io.swagger.service.MyUserDetailsService;
import io.swagger.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")
@RestController
public class AccountsApiController implements AccountsApi {

    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    MyUserDetailsService myUserDetailsService;

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    //werkt
    public ResponseEntity createAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody CreateAccount body) {
        Status defaultStatus = Status.Active;
        double defaultLimit = 0.00;
        if(body.getActive() == null){
            body.setActive(Status.Active);
        }
        if(body.getAbsoluteLimit() == null){
            body.setAbsoluteLimit(defaultLimit);
        }

        if(myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)){
            body.setOwner(myUserDetailsService.getLoggedInUser().getUsername());

        }
        else if(body.getOwner() == null)
        {
            log.error("Owner was not given.");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.BAD_REQUEST);
        }
        if(userService.getUserByUsername(body.getOwner()) != null){
            AccountWithTransactions createAccount = accountService.createNewAccount(body);

            log.info("Account created for user : " + body.getOwner());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createAccount);
        }
        else{
            log.error("Could Not Find User For The Given Owner, Wrong Input?");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.NOT_FOUND);
        }
    }

    //werkt
    public ResponseEntity<AccountWithTransactions> getAccount(@Parameter(in = ParameterIn.PATH, description = "Iban of the account", required = true, schema = @Schema()) @PathVariable("iban") String iban) {

        if (accountService.accountExist(iban) == true) {
            return new ResponseEntity<AccountWithTransactions>(accountService.getAccountByIban(iban), HttpStatus.OK);
        } else {
            log.error("Could Not Find Accounts For The Given IBAN, Wrong Input?");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.NOT_FOUND);
        }

    }

    //werkt
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<List<AllAccountsWithoutTransactions>> getAllAccounts(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip", schema = @Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset, @Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit, @Parameter(in = ParameterIn.QUERY, description = "Get accounts from person with this first name or last name", schema = @Schema()) @Valid @RequestParam(value = "name", required = false) String name, @Parameter(in = ParameterIn.QUERY, description = "Get accounts from person with this username", schema = @Schema()) @Valid @RequestParam(value = "username", required = false) String username, @Parameter(in = ParameterIn.QUERY, description = "Get accounts from person with this email", schema = @Schema()) @Valid @RequestParam(value = "email", required = false) String email) {
        long defaultOffset = 0;
        long defaultLimit = 10;
        try {
            if(offset == null){
                offset = defaultOffset;
            }
            if(limit == null){
                limit = defaultLimit;
            }
            if(name != null || username != null || email != null){
                if(name != null){
                    if(username == null){
                        username = name;
                    }
                    if(email == null){
                        email = name;
                    }
                }
                if(username != null){
                    if(name == null){
                        name = username;
                    }
                    if(email == null){
                        email = username;
                    }
                }
                if(email != null){
                    if(name == null){
                        name = email;
                    }
                    if(username == null){
                        username = email;
                    }
                }
                User getUserByInput = userService.getuserByInput(name, username, email);
                List<AllAccountsWithoutTransactions> allAccountsWithoutTransactionsList = accountService.changingFromWithToWithoutTransaction(accountService.getAllAccountsByUserid(offset, limit, getUserByInput.getId()));
                return new ResponseEntity<List<AllAccountsWithoutTransactions>>(allAccountsWithoutTransactionsList, HttpStatus.OK);
            }
            else{
                List<AllAccountsWithoutTransactions> allAccountsWithoutTransactionsList = accountService.changingFromWithToWithoutTransaction(accountService.getAllAccounts());
                return new ResponseEntity<List<AllAccountsWithoutTransactions>>(allAccountsWithoutTransactionsList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<AllAccountsWithoutTransactions>>(HttpStatus.BAD_REQUEST);
        }
    }

    //werkt
    public ResponseEntity<List<AccountWithTransactions>> getUserAccounts(@Parameter(in = ParameterIn.PATH, description = "User of accounts to get", required = true, schema = @Schema()) @PathVariable("username") String username) {
        try {
            if (userService.usernameAlreadyExist(username) == true) ;
            {
                User user = userService.getUserByUsername(username);
                return new ResponseEntity<List<AccountWithTransactions>>(accountService.getAllAccountsByUser(user), HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Could Not Find Accounts For User, Wrong Input?");
            return new ResponseEntity<List<AccountWithTransactions>>(HttpStatus.NOT_FOUND);
        }
    }

    //werkt
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<Void> updateAcount(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "Update account", required = true, schema = @Schema()) @Valid @RequestBody UpdateAccountDTO body) {

        if(body.status == Status.Active || body.status == Status.Inactive){
            AccountWithTransactions account = accountService.getAccountByIban(iban);
            if(account != null){
                log.info("Updating Account...");
                System.out.println("Updated Account for User. " + account.getOwner());
                System.out.println("    previously; AccountStatus : "+ account.getActive()+ ", AbsoluteLimit : "+ account.getAbsoluteLimit());

                accountService.updateAccount(body, account);
                System.out.println("-----------------------------------------------------------------");
                System.out.println("    Updated Status To : " + "'" + account.getActive() + "'" + ", Updated AbsoluteLimit To : " + "'" +account.getAbsoluteLimit() + "'.");
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
            else {
                log.info("No Account Found With Given IBAN, Wrong Input?");
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            log.error("Wrong Account Status Input?");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
}
