package io.swagger.api;

import io.swagger.model.*;
import io.swagger.service.AccountService;
import io.swagger.service.MyUserDetailsService;
import io.swagger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.threeten.bp.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-03T11:13:57.329Z[GMT]")
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

    public ResponseEntity createDeposit(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody DepositRequestBody body) {
        String accept = request.getHeader("Accept");

        Deposit createDeposit = transactionService.createDeposit(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createDeposit);
    }

    public ResponseEntity<Transaction> createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody TransactionRequestBody body) {
        String accept = request.getHeader("Accept");

        if (accountService.accountExist(body.getAccountFrom())){
            if (accountService.accountExist(body.getAccountTo())){
                Transaction createTransaction = transactionService.createTransaction(body, myUserDetailsService.getLoggedInUser().getUsername());

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(createTransaction);
            }
            else {
                log.error("Iban doesnt exist");
                return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
            }
        }

        else {
            log.error("Iban from doesnt exist");
            return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
        }
//        myUserDetailsService.getLoggedInUser().getUsername();


    }

    public ResponseEntity createWhitdrawal(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody WithdrawalRequestBody body) {
        String accept = request.getHeader("Accept");

        Withdrawal createWithdrawal = transactionService.createWithdrawal(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createWithdrawal);

    }

    public ResponseEntity<List<Transaction>> getTransactions(@Parameter(in = ParameterIn.QUERY, description = "amount of transaction to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset,@Parameter(in = ParameterIn.QUERY, description = "limit of transactions to get" ,schema=@Schema( defaultValue="50")) @Valid @RequestParam(value = "limit", required = false, defaultValue="50") Long limit,@Parameter(in = ParameterIn.QUERY, description = "Get all transactions from this date" ,schema=@Schema()) @Valid @RequestParam(value = "dateFrom", required = false) LocalDate dateFrom,@Parameter(in = ParameterIn.QUERY, description = "Get all transactions to this date" ,schema=@Schema()) @Valid @RequestParam(value = "dateTo", required = false) LocalDate dateTo) {
        String accept = request.getHeader("Accept");
        System.out.println("You have called all transactions");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<Transaction>>(transactionService.getAllTransactions(), HttpStatus.OK);
        }
        log.error("No transactions found");
        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Transaction>> getTransactionsByIban(@Parameter(in = ParameterIn.PATH, description = "Iban from the transactions you want to see", required=true, schema=@Schema()) @PathVariable("iban") String iban,@Parameter(in = ParameterIn.QUERY, description = "amount of transaction to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset,@Parameter(in = ParameterIn.QUERY, description = "limit of transactions to get" ,schema=@Schema( defaultValue="50")) @Valid @RequestParam(value = "limit", required = false, defaultValue="50") Long limit,@Parameter(in = ParameterIn.QUERY, description = "Get all transactions from this date" ,schema=@Schema()) @Valid @RequestParam(value = "dateFrom", required = false) LocalDate dateFrom,@Parameter(in = ParameterIn.QUERY, description = "Get all transactions to this date" ,schema=@Schema()) @Valid @RequestParam(value = "dateTo", required = false) LocalDate dateTo) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Transaction>>(objectMapper.readValue("[ {\n  \"transactionType\" : \"Transaction\",\n  \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n  \"amount\" : 6.027456183070403,\n  \"userPerforming\" : \"BG12345\",\n  \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n  \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n}, {\n  \"transactionType\" : \"Transaction\",\n  \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n  \"amount\" : 6.027456183070403,\n  \"userPerforming\" : \"BG12345\",\n  \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n  \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
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
