package io.swagger.api;

import io.swagger.model.*;
import io.swagger.service.AccountService;
import io.swagger.service.MyUserDetailsService;
import io.swagger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")
@RestController
public class TransactionsApiController implements TransactionsApi {

    @Autowired
    TransactionService transactionService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Deposit> createDeposit(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody DepositRequestBody body) {
        String accept = request.getHeader("Accept");

        Deposit createDeposit = transactionService.createDeposit(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createDeposit);
    }

    public ResponseEntity<Transaction> createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody TransactionRequestBody body) {
        String accept = request.getHeader("Accept");

        Transaction createTransaction = transactionService.createTransaction(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createTransaction);
    }

    public ResponseEntity<Withdrawal> createWhitdrawal(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody WithdrawalRequestBody body) {
        String accept = request.getHeader("Accept");

        Withdrawal createWithdrawal = transactionService.createWithdrawal(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createWithdrawal);

    }

    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity getTransactions(@Parameter(in = ParameterIn.QUERY, description = "amount of transaction to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset,@Parameter(in = ParameterIn.QUERY, description = "limit of transactions to get" ,schema=@Schema( defaultValue="50")) @Valid @RequestParam(value = "limit", required = false, defaultValue="50") Long limit,@Parameter(in = ParameterIn.QUERY, description = "Get all transactions from this date; Format (dd/MM/yyyy (HH))" ,schema=@Schema()) @Valid @RequestParam(value = "dateFrom", required = false) String dateFrom,@Parameter(in = ParameterIn.QUERY, description = "Get all transactions to this date; Format (dd/MM/yyyy (HH))" ,schema=@Schema()) @Valid @RequestParam(value = "dateTo", required = false) String dateTo, @Parameter(in = ParameterIn.QUERY, description = "Get all transactions from this type" ,schema=@Schema()) @Valid @RequestParam(value = "transactionType", required = false) String transactionType) {
        List<TransactionReponse> transactions = new ArrayList<>();

        try {
            transactions = transactionService.getAllTransactions(limit, offset, dateFrom, dateTo, transactionType);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        if (transactions.size() < 1) {
            return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<TransactionReponse>>(transactions, HttpStatus.OK);
    }

    public ResponseEntity getTransactionsByIban(@Parameter(in = ParameterIn.PATH, description = "Iban from the transactions you want to see", required=true, schema=@Schema()) @PathVariable("iban") String iban,@Parameter(in = ParameterIn.QUERY, description = "amount of transaction to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset,@Parameter(in = ParameterIn.QUERY, description = "limit of transactions to get" ,schema=@Schema( defaultValue="50")) @Valid @RequestParam(value = "limit", required = false, defaultValue="50") Long limit,@Parameter(in = ParameterIn.QUERY, description = "Get all transactions from this date" ,schema=@Schema()) @Valid @RequestParam(value = "dateFrom", required = false) String dateFrom,@Parameter(in = ParameterIn.QUERY, description = "Get all transactions to this date" ,schema=@Schema()) @Valid @RequestParam(value = "dateTo", required = false) String dateTo, @Parameter(in = ParameterIn.QUERY, description = "Get all transactions from this type" ,schema=@Schema()) @Valid @RequestParam(value = "transactionType", required = false) String transactionType) {
        List<TransactionReponse> transactions = new ArrayList<>();

        AccountWithTransactions account = accountService.getAccountByIban(iban);
        Boolean loggedInUserIsCustomer = myUserDetailsService.getLoggedInUser().getAuthorities().contains(UserRole.ROLE_Customer);
        if (account == null) {
            if (loggedInUserIsCustomer){
                return new ResponseEntity<TransactionReponse>(HttpStatus.FORBIDDEN);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Iban is invalid");
        } if (loggedInUserIsCustomer && account.getOwner().getUsername() != myUserDetailsService.getLoggedInUser().getUsername()){
            return new ResponseEntity<TransactionReponse>(HttpStatus.FORBIDDEN);
        }

        try {
            transactions = transactionService.getAllTransactionsByIban(account, limit, offset, dateFrom, dateTo, transactionType);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        if (transactions.size() < 1) {
            return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<TransactionReponse>>(transactions, HttpStatus.OK);
    }

//    public ResponseEntity<List<Transaction>> getTransactionsByIban(@Parameter(in = ParameterIn.PATH, description = "Iban from the transactions you want to see", required=true, schema=@Schema()) @PathVariable("iban") String iban) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            return new ResponseEntity<List<Transaction>>(transactionService.getTransactionsByIban(iban), HttpStatus.OK);
//        }
//
//        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
//    }

}
