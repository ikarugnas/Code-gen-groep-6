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


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-03T11:13:57.329Z[GMT]")
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

    // create account
    public ResponseEntity createAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody CreateAccount body) {
        Status defaultStatus = Status.Active; // default status
        double defaultLimit = 0.00; // default limit
        if (body.getActive() == null) { // if status is null implement default status
            body.setActive(Status.Active);
        }
        if (body.getAbsoluteLimit() == null) { // if absolutelimit is null implement default limit
            body.setAbsoluteLimit(defaultLimit);
        }
        if (AccountType.fromValue(body.getType()) == null) { // check if given type is written good, if not return unprocessable entity
            log.error("Type Was Not Given Correctly, Must Be 'Current' Or 'Savings'");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)) { // if logged in user is not of role employee and user tries to create account for other users, user is given forbidden
            if (body.getOwner() != null && myUserDetailsService.getLoggedInUser().getUsername().contains(body.getOwner()) == false) {
                log.error("Your Not Allowed To Create Accounts For Other Users. If You Wish To Create An Account For Yourself Please Enter Your Own Username");
                return new ResponseEntity<AccountWithTransactions>(HttpStatus.FORBIDDEN);
            }
        } else if (body.getOwner() == null) { // if owner is not given, return unprocessable entity
            log.error("Owner Username was not given.");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (userService.getUserByUsername(body.getOwner()) != null) { // if given owner of new account is not null, create account
            AccountWithTransactions createAccount = accountService.createNewAccount(body);
            log.info("Account created for user : " + body.getOwner());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createAccount);
        } else { // if user is with given username is not found, return not found
            log.error("Could Not Find User For The Given Owner Username, Wrong Input?");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.NOT_FOUND);
        }
    }

    // get account with iban
    public ResponseEntity<AccountWithTransactions> getAccount(@Parameter(in = ParameterIn.PATH, description = "Iban of the account", required = true, schema = @Schema()) @PathVariable("iban") String iban) {
        AccountWithTransactions bank = accountService.getAccountByIban("NL01INHO0000000001");
        User bankUser = userService.getUserByUsername("bank");
        User loggedInUser = userService.getUserByUsername(myUserDetailsService.getLoggedInUser().getUsername()); // get user object for logged in user
        if (accountService.accountExist(iban) == true) { // check if iban exists
            if (myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)) { // check if logged in user is of role Customer

                List<AccountWithTransactions> accountWithTransactions = accountService.getAllAccountsByUser(loggedInUser); // get accounts with user object
                for (AccountWithTransactions a : accountWithTransactions) { // iterate through all accounts of user till given iban is found
                    if (a.getIban() == iban) {
                        return new ResponseEntity<AccountWithTransactions>(accountService.getAccountByIban(iban), HttpStatus.OK); // return account with given iban
                    }
                }
            } else if(myUserDetailsService.getLoggedInUser().getUsername() == "bank") { // if logged in user is of role Employee get account with iban
                {
                    if (iban.toString() == bank.getIban() ){ // if iban is of bank account
                        if (loggedInUser.getUsername() == bankUser.getUsername()) { // if the logged in user is bank return account
                            return new ResponseEntity<AccountWithTransactions>(accountService.getAccountByIban(iban), HttpStatus.OK);
                        } else { // else return forbidden
                            return new ResponseEntity<AccountWithTransactions>(HttpStatus.FORBIDDEN);
                        }
                    } else { // if iban is not from bank account return ok with account
                        return new ResponseEntity<AccountWithTransactions>(accountService.getAccountByIban(iban), HttpStatus.OK);
                    }
                }
            }
        } else { // if iban doesnt exist throw not found
            log.error("Could Not Find Accounts For The Given IBAN, Wrong Input?");
            return new ResponseEntity<AccountWithTransactions>(HttpStatus.NOT_FOUND);
        }
        log.error("The Account You Are Trying To Find Is Not Yours!"); // if given iban is not an account of logged in user throw forbidden
        return new ResponseEntity<AccountWithTransactions>(HttpStatus.FORBIDDEN);
    }

    //get all accounts for everyone, or for a certain user
    @PreAuthorize("hasRole('Employee')") // access for employee only
    public ResponseEntity<List<AllAccountsWithoutTransactions>> getAllAccounts(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip", schema = @Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset, @Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit, @Parameter(in = ParameterIn.QUERY, description = "Get accounts from user (Can be name, Email or username)", schema = @Schema()) @Valid @RequestParam(value = "user", required = false) String user) {
        String searchString = ""; // input by user as value "user", can be of String type name, username or email

        if (user != null) { // if user is not null, get account for
            searchString = user.toLowerCase();

            User getUserByInput = userService.getuserByInput(searchString); // get user to find all acounts for
            List<AllAccountsWithoutTransactions> allAccountsWithoutTransactionsList = accountService.changingFromWithToWithoutTransaction(accountService.getAllAccountsByUserid(offset, limit, getUserByInput.getId())); // get all accounts for user
            return new ResponseEntity<List<AllAccountsWithoutTransactions>>(allAccountsWithoutTransactionsList, HttpStatus.OK);// return all accounts for user
        } else { // return all accounts
            List<AllAccountsWithoutTransactions> allAccountsWithoutTransactionsList = accountService.changingFromWithToWithoutTransaction(accountService.getAllAccounts(offset, limit));
            return new ResponseEntity<List<AllAccountsWithoutTransactions>>(allAccountsWithoutTransactionsList, HttpStatus.OK);
        }
    }

    // get account for user with username
    public ResponseEntity<List<AccountWithTransactions>> getUserAccounts(@Parameter(in = ParameterIn.PATH, description = "User of accounts to get", required = true, schema = @Schema()) @PathVariable("username") String username) {
        User loggedInUser = userService.getUserByUsername(myUserDetailsService.getLoggedInUser().getUsername()); // get user object for logged in user
        User bank = userService.getUserByUsername("bank");


        if (userService.usernameAlreadyExist(username) == true) { // if given username exists
            User user = userService.getUserByUsername(username); // get user object for username
            if (myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer)) { // check if logged in user is of role type customer
                if (username == loggedInUser.getUsername()) { // check if given username is equal to logged in user, if it is the same user, return accounts
                    return new ResponseEntity<List<AccountWithTransactions>>(accountService.getAllAccountsByUser(user), HttpStatus.OK);
                } else { // return forbidden
                    log.error("Forbidden. A Customer Cannot Access Other User's Accounts.");
                    return new ResponseEntity<List<AccountWithTransactions>>(HttpStatus.FORBIDDEN);
                }
            } else {// if logged in user is of type employee return user
                if(username.toString() == bank.getUsername().toString()){
                    System.out.println("is bank");
                }
                return new ResponseEntity<List<AccountWithTransactions>>(accountService.getAllAccountsByUser(user), HttpStatus.OK);
            }
        } else {// return not found
            log.error("Could Not Find Accounts For User, Wrong Input?");
            return new ResponseEntity<List<AccountWithTransactions>>(HttpStatus.NOT_FOUND);
        }
    }

    // update account for given iban
    @PreAuthorize("hasRole('Employee')") // access for employee only
    public ResponseEntity<Void> updateAcount(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "Update account", required = true, schema = @Schema()) @Valid @RequestBody UpdateAccountDTO body) {

        if (body.status == Status.Active || body.status == Status.Inactive) { // check if given status is given correctly
            AccountWithTransactions account = accountService.getAccountByIban(iban); // get account to update with given iban
            if (account != null) { // if account exists do the following
                log.info("Updating Account...");
                System.out.println("Updated Account for User. " + account.getOwner());
                System.out.println("    previously; AccountStatus : " + account.getActive() + ", AbsoluteLimit : " + account.getAbsoluteLimit());

                accountService.updateAccount(body, account);
                System.out.println("-----------------------------------------------------------------");
                System.out.println("    Updated Status To : " + "'" + account.getActive() + "'" + ", Updated AbsoluteLimit To : " + "'" + account.getAbsoluteLimit() + "'.");
                return new ResponseEntity<Void>(HttpStatus.OK); // update and return ok
            } else {
                log.info("No Account Found With Given IBAN, Wrong Input?"); // if no accounts are found with given iban return not found
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        } else {
            log.error("Wrong Account Status Input?"); // if status is not given correctly return bad request
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
}
