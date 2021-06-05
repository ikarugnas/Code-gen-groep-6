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
import java.util.Locale;
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
        if (body.getActive() == null) {
            body.setActive(Status.Active);
        }
        if (body.getAbsoluteLimit() == null) {
            body.setAbsoluteLimit(defaultLimit);
        }
//        accountService.isValueOfType(body.getType())
        if(AccountType.fromValue(body.getType()) == null){
            log.error("Type Was Not Given Correctly, Must Be 'Current' Or 'Savings'");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)) {
            if(body.getOwner() != null && myUserDetailsService.getLoggedInUser().getUsername().contains(body.getOwner()) == false){
                log.error("Your Not Allowed To Create Accounts For Other Users. If You Wish To Create An Account For Yourself Please Enter Your Own Username");
                return new ResponseEntity<AccountWithTransactions>(HttpStatus.FORBIDDEN);
            }
        } else if (body.getOwner() == null) {
            log.error("Owner Username was not given.");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (userService.getUserByUsername(body.getOwner()) != null) {
            AccountWithTransactions createAccount = accountService.createNewAccount(body);

            log.info("Account created for user : " + body.getOwner());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createAccount);
        } else {
            log.error("Could Not Find User For The Given Owner Username, Wrong Input?");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.NOT_FOUND);
        }
    }

    //werkt
    public ResponseEntity<AccountWithTransactions> getAccount(@Parameter(in = ParameterIn.PATH, description = "Iban of the account", required = true, schema = @Schema()) @PathVariable("iban") String iban) {
        if (accountService.accountExist(iban) == true) {
            if (myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)) {
                User loggedInUser = userService.getUserByUsername(myUserDetailsService.getLoggedInUser().getUsername());
                List<AccountWithTransactions> accountWithTransactions = accountService.getAllAccountsByUser(loggedInUser);
                for (AccountWithTransactions a : accountWithTransactions) {
                    if (a.getIban() == iban) {
                        return new ResponseEntity<AccountWithTransactions>(accountService.getAccountByIban(iban), HttpStatus.OK);
                    }
                }
            } else {
                {
                    return new ResponseEntity<AccountWithTransactions>(accountService.getAccountByIban(iban), HttpStatus.OK);
                }

            }
        }
        else {
            log.error("Could Not Find Accounts For The Given IBAN, Wrong Input?");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.NOT_FOUND);
        }
        log.error("The Account You Are Trying To Find Is Not Yours!");
        return new ResponseEntity<AccountWithTransactions>(HttpStatus.FORBIDDEN);

    }

    //werkt
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<List<AllAccountsWithoutTransactions>> getAllAccounts(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip", schema = @Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset, @Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit, @Parameter(in = ParameterIn.QUERY, description = "Get accounts from user (Can be name, Email or username)", schema = @Schema()) @Valid @RequestParam(value = "user", required = false) String user) {
        String searchString = "";

        if (user != null) {
            searchString = user.toLowerCase();

            User getUserByInput = userService.getuserByInput(searchString);
            List<AllAccountsWithoutTransactions> allAccountsWithoutTransactionsList = accountService.changingFromWithToWithoutTransaction(accountService.getAllAccountsByUserid(offset, limit, getUserByInput.getId()));
            log.info("");
            return new ResponseEntity<List<AllAccountsWithoutTransactions>>(allAccountsWithoutTransactionsList, HttpStatus.OK);
        } else {
            List<AllAccountsWithoutTransactions> allAccountsWithoutTransactionsList = accountService.changingFromWithToWithoutTransaction(accountService.getAllAccounts(offset, limit));
            return new ResponseEntity<List<AllAccountsWithoutTransactions>>(allAccountsWithoutTransactionsList, HttpStatus.OK);
        }
    }

    //werkt
    public ResponseEntity<List<AccountWithTransactions>> getUserAccounts(@Parameter(in = ParameterIn.PATH, description = "User of accounts to get", required = true, schema = @Schema()) @PathVariable("username") String username) {
        User loggedInUser = userService.getUserByUsername(myUserDetailsService.getLoggedInUser().getUsername());

        if (userService.usernameAlreadyExist(username) == true) {
            User user = userService.getUserByUsername(username);
            if (myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)) {
                if (username == loggedInUser.getUsername()) {
                    return new ResponseEntity<List<AccountWithTransactions>>(accountService.getAllAccountsByUser(user), HttpStatus.OK);
                } else {
                    log.error("Forbidden. A Customer Cannot Access Other User's Accounts.");
                    return new ResponseEntity<List<AccountWithTransactions>>(HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<List<AccountWithTransactions>>(accountService.getAllAccountsByUser(user), HttpStatus.OK);
            }
        } else {
            log.error("Could Not Find Accounts For User, Wrong Input?");
            return new ResponseEntity<List<AccountWithTransactions>>(HttpStatus.NOT_FOUND);
        }
    }

    //werkt
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<Void> updateAcount(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "Update account", required = true, schema = @Schema()) @Valid @RequestBody UpdateAccountDTO body) {

        if (body.status == Status.Active || body.status == Status.Inactive) {
            AccountWithTransactions account = accountService.getAccountByIban(iban);
            if (account != null) {
                log.info("Updating Account...");
                System.out.println("Updated Account for User. " + account.getOwner());
                System.out.println("    previously; AccountStatus : " + account.getActive() + ", AbsoluteLimit : " + account.getAbsoluteLimit());

                accountService.updateAccount(body, account);
                System.out.println("-----------------------------------------------------------------");
                System.out.println("    Updated Status To : " + "'" + account.getActive() + "'" + ", Updated AbsoluteLimit To : " + "'" + account.getAbsoluteLimit() + "'.");
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                log.info("No Account Found With Given IBAN, Wrong Input?");
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        } else {
            log.error("Wrong Account Status Input?");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
}
