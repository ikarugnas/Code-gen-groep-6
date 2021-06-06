package io.swagger.configuration;


import io.swagger.model.RegisterDTO;
import io.swagger.model.User;
import io.swagger.model.UserRole;
import io.swagger.model.*;
import io.swagger.repository.AccountRepository;
import io.swagger.model.Status;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.UserRepository;
import io.swagger.service.AccountService;
import io.swagger.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@ConditionalOnProperty(prefix = "bankingAPI.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Applicationrunner implements ApplicationRunner {


    UserRepository userRepository;
    AccountService accountService;
    AccountRepository accountRepository;
    UserService userService;
    TransactionRepository transactionRepository;

    public Applicationrunner(UserRepository userRepository, UserService userService, AccountService accountService, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users =
                Arrays.asList(
                        new User("test1", "test1", "testname1", "test1@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, Status.Active),
                        new User("test2", "test2", "testname2", "test2@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, Status.Active),
                        new User("test3", "test3", "testname3", "test3@gmail.com", UserRole.ROLE_Customer, 21.23, 200.00, Status.Active)
                        );

        users.forEach(userRepository::save);

        RegisterDTO customer = new RegisterDTO("customer", "hoi", "customer hoi", "customer@bankapi.com");
        RegisterDTO employee = new RegisterDTO("employee", "hoi", "employee hoi", "employee@bankapi.com", UserRole.ROLE_Employee);
        RegisterDTO inactiveUser = new RegisterDTO("inactive", "hoi", "inactive hoi", "inactive@bankapi.com");
        inactiveUser.setUserStatus(Status.Inactive);

        userService.createUser(customer);
        userService.createUser(employee);

        // create bank user to create a bank account
        RegisterDTO bank = new RegisterDTO("bank", "bank123", "Inholland Bank ", "inhollandBank@bankapi.com", UserRole.ROLE_Employee);
        userService.createUser(bank);

        // create bank account which is a requirement
        AccountWithTransactions bankAccount = new AccountWithTransactions("NL01INHO0000000001", 10000.00, AccountType.Current, userService.getUserByUsername("bank"), 0.00, Status.Active, CurrencyType.EUR);
        accountService.createBankAccount(bankAccount);

        // test account from burak branch
        AccountWithTransactions account3 = new AccountWithTransactions("NL01INHO0000000002", 0.00, AccountType.Current, userService.getUserByUsername("test1"), 0.00, Status.Active, CurrencyType.EUR);
        accountService.createBankAccount(account3);

        // from main
        CreateAccount account2 = new CreateAccount(0.00, Status.Active, "test1", "Current", CurrencyType.EUR);
        AccountWithTransactions accountWithTransactions = accountService.createNewAccount(account2);

        // Create test transactions
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH");
        Date date1 = dateFormat.parse("21/11/2020 20");
        Date date2 = dateFormat.parse("10/11/2020 09");


        List<Transaction> transactions = Arrays.asList(
                new Transaction("Employee", accountWithTransactions, bankAccount, 20.0, Transaction.TransactionTypeEnum.TRANSACTION, new Timestamp(date1.getTime())),
                new Transaction("Employee", bankAccount, accountWithTransactions, 20.0, Transaction.TransactionTypeEnum.TRANSACTION, new Timestamp(date2.getTime()))
        );

        transactions.forEach(transactionRepository::save);

        userService.createUser(inactiveUser);
    }

}
