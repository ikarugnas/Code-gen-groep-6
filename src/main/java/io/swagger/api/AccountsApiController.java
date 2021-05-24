package io.swagger.api;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.AllAccountsWithoutTransactions;
import io.swagger.model.CreateAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.AccountService;
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
public class AccountsApiController implements AccountsApi {

    @Autowired
    AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity createAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateAccount body) {
        String accept = request.getHeader("Accept");

        AccountWithTransactions createAccount = accountService.createAccount(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createAccount);
    }

    public ResponseEntity<List<AccountWithTransactions>> getAccount(@Parameter(in = ParameterIn.PATH, description = "Iban of the account", required=true, schema=@Schema()) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<AccountWithTransactions>>(objectMapper.readValue("[ {\n  \"owner\" : \"owner\",\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 0,\n  \"iban\" : \"NL55 RABO 1234 5678 90\",\n  \"active\" : \"Active\",\n  \"type\" : \"Current\",\n  \"transaction\" : [ {\n    \"transactionType\" : \"Transaction\",\n    \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n    \"amount\" : 6.027456183070403,\n    \"userPerforming\" : \"BG12345\",\n    \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n    \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n  }, {\n    \"transactionType\" : \"Transaction\",\n    \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n    \"amount\" : 6.027456183070403,\n    \"userPerforming\" : \"BG12345\",\n    \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n    \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n  } ]\n}, {\n  \"owner\" : \"owner\",\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 0,\n  \"iban\" : \"NL55 RABO 1234 5678 90\",\n  \"active\" : \"Active\",\n  \"type\" : \"Current\",\n  \"transaction\" : [ {\n    \"transactionType\" : \"Transaction\",\n    \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n    \"amount\" : 6.027456183070403,\n    \"userPerforming\" : \"BG12345\",\n    \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n    \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n  }, {\n    \"transactionType\" : \"Transaction\",\n    \"accountTo\" : \"NL55 RABO 1234 5678 90\",\n    \"amount\" : 6.027456183070403,\n    \"userPerforming\" : \"BG12345\",\n    \"dateAndTime\" : \"2016-08-29T09:12:33.001Z\",\n    \"accountFrom\" : \"NL55 RABO 1234 5678 90\"\n  } ]\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<AccountWithTransactions>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<AccountWithTransactions>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<AllAccountsWithoutTransactions>> getAllAccounts(@Parameter(in = ParameterIn.QUERY, description = "amount of accounts to skip" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Long offset,@Parameter(in = ParameterIn.QUERY, description = "limit of accounts to get" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Long limit,@Parameter(in = ParameterIn.QUERY, description = "Get accounts from person with this first name or last name" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name,@Parameter(in = ParameterIn.QUERY, description = "Get accounts from person with this username" ,schema=@Schema()) @Valid @RequestParam(value = "username", required = false) String username,@Parameter(in = ParameterIn.QUERY, description = "Get accounts from person with this email" ,schema=@Schema()) @Valid @RequestParam(value = "email", required = false) String email) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<AllAccountsWithoutTransactions>>(objectMapper.readValue("[ {\n  \"owner\" : \"owner\",\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 0,\n  \"iban\" : \"NL55 RABO 1234 5678 90\",\n  \"active\" : \"Active\",\n  \"type\" : \"Current\"\n}, {\n  \"owner\" : \"owner\",\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 0,\n  \"iban\" : \"NL55 RABO 1234 5678 90\",\n  \"active\" : \"Active\",\n  \"type\" : \"Current\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<AllAccountsWithoutTransactions>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<AllAccountsWithoutTransactions>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<AllAccountsWithoutTransactions>> getUserAccounts(@Parameter(in = ParameterIn.PATH, description = "User of accounts to get", required=true, schema=@Schema()) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<AllAccountsWithoutTransactions>>(objectMapper.readValue("[ {\n  \"owner\" : \"owner\",\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 0,\n  \"iban\" : \"NL55 RABO 1234 5678 90\",\n  \"active\" : \"Active\",\n  \"type\" : \"Current\"\n}, {\n  \"owner\" : \"owner\",\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 0,\n  \"iban\" : \"NL55 RABO 1234 5678 90\",\n  \"active\" : \"Active\",\n  \"type\" : \"Current\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<AllAccountsWithoutTransactions>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<AllAccountsWithoutTransactions>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateAcount(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("iban") String iban,@Parameter(in = ParameterIn.DEFAULT, description = "Update account", required=true, schema=@Schema()) @Valid @RequestBody AccountWithTransactions body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
