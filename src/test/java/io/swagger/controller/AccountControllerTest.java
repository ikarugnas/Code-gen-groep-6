package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.AccountsApi;
import io.swagger.model.*;
import io.swagger.service.AccountService;
import io.swagger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountsApi api;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UserService userService;

    private User bank;
    private AccountWithTransactions account;
    private CreateAccount createAccount;
    private AllAccountsWithoutTransactions testAccount;
    private User testUser;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void initialize(){
        bank = new User("bank", "bank123", "Inholland Bank ", "inhollandBank@bankapi.com", UserRole.ROLE_Employee, 1000.00, 500.00, Status.Active);
        testUser = new User("testuser", "test123", "test5 ", "testUser5@gmail.com", UserRole.ROLE_Customer, 1000.00, 500.00, Status.Active);
        createAccount = new CreateAccount(0.0, Status.Active, bank.getUsername(),"Current", CurrencyType.EUR);
        account = new AccountWithTransactions("NL01INHO0000000002", 0.00, AccountType.Current, testUser, 0.00, Status.Active, CurrencyType.EUR);
    }

    //region CreateAccount
    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeCreateAccountWithEmptyOwnerShouldReturn422() throws Exception{
        this.mvc.perform(post("/accounts")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content("{\n" +
                "  \"type\": \"Current\"\n" +
                "}"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeCreateAccountWithEmptyTypeShouldReturn422() throws Exception{
        this.mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"owner\": \"test1\"\n" +
                        "}"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeCreateAccountWithFilledPropertiesShouldReturn201() throws Exception{
        this.mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"owner\": \"test1\",\n" +
                        "  \"type\": \"Current\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeCreateAccountWithWrongGivenUsernameShouldReturn404() throws Exception{
        this.mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"owner\": \"customaaer\",\n" +
                        "  \"type\": \"Current\"\n" +
                        "}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeCreateAccountWithWrongGivenTypeShouldReturn400() throws Exception{
        this.mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"owner\": \"test1\",\n" +
                        "  \"type\": \"Cuarrent\"\n" +
                        "}"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithMockUser(username = "Customer", password = "Customer1", roles = "Customer")
    public void CustomerCreateAccountForOtherUsersShouldReturn403() throws Exception{
        this.mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"owner\": \"test1\",\n" +
                        "  \"type\": \"Current\"\n" +
                        "}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "Customer", password = "Customer1", roles = "Customer")
    public void CustomerCreateAccountWithWrongTypeShouldReturn400() throws Exception{
        this.mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"owner\": \"Customer\",\n" +
                        "  \"type\": \"Cuarrent\"\n" +
                        "}"))
                .andExpect(status().isUnprocessableEntity());
    }
//endregion

    //region GetAccountWithIban
    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeGetAccountWithCorrectIbanShouldReturn200() throws Exception {
        given(accountService.getAccountByIban(account.getIban())).willReturn(account);
        given(accountService.accountExist(account.getIban())).willReturn(true);
        this.mvc.perform(
                get("/accounts/{iban}","NL01INHO0000000002"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.iban").value(account.getIban()));
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeGetAccountWithNotExistingIbanShouldReturn404() throws Exception{
        given(accountService.getAccountByIban(account.getIban())).willReturn(account);
        given(accountService.accountExist(account.getIban())).willReturn(true);
        this.mvc.perform(
                get("/accounts/{iban}","NL01INHO0000000005"))
                .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(username = "testuser", password = "test123", roles = "Customer")
    public void CustomerGetAccountWithOthersIbanShouldReturn403() throws Exception{
        given(accountService.accountExist(account.getIban())).willReturn(true);
        given(accountService.getAccountByIban(account.getIban())).willReturn(account);
        this.mvc.perform(
                get("/accounts/{iban}","NL01INHO0000000002"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "testuser", password = "test123", roles = "Customer")
    public void CustomerGetAccountWithNotExistingIbanShouldReturn404() throws Exception{
        given(accountService.getAccountByIban(account.getIban())).willReturn(account);
        given(accountService.accountExist(account.getIban())).willReturn(true);
        this.mvc.perform(
                get("/accounts/{iban}","NL01INHO0000000005"))
                .andExpect(status().isNotFound());
    }
    //endregion

    //region GetAllAccounts

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeGetAllAccountsWithNoParametersShouldReturn200() throws Exception {
        this.mvc.perform(
                get("/accounts/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeGetAllAccountsWithUsernameShouldReturn200() throws Exception {
        this.mvc.perform(
                get("/accounts/", testUser.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeGetAllAccountsWithNameShouldReturn200() throws Exception {
        this.mvc.perform(
                get("/accounts/", testUser.getName()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeGetAllAccountsWithEmaiShouldReturn200() throws Exception {
        this.mvc.perform(
                get("/accounts/", testUser.getEmail()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Customer", password = "Customer1", roles = "Customer")
    public void CustomerGetAllAccountsShouldReturn403() throws Exception {
        this.mvc.perform(
                get("/accounts/"))
                .andExpect(status().isOk());
    }
    //endregion

    //region GetAccountWithUername

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeGetAccountWithCorrectUsernameShouldReturn200() throws Exception {
        given(userService.usernameAlreadyExist("testuser")).willReturn(true);

        this.mvc.perform(
                get("/accounts/{username}","testuser"))
                .andExpect(status().isOk());
    }




    //endregion

    //region UpdateAccount

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeUpdateAccountWithCorrectIbanAndCorrectBodyShouldReturn200() throws Exception {
        given(accountService.getAccountByIban(account.getIban())).willReturn(account);
        given(accountService.accountExist(account.getIban())).willReturn(true);
        this.mvc.perform(
                put("/accounts/{iban}","NL01INHO0000000002")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"absoluteLimit\": 0,\n" +
                        "  \"status\": \"Inactive\"\n" +
                        "}"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeUpdateAccountWithCorrectIbanAndWrongBodyShouldReturn400() throws Exception {
        given(accountService.getAccountByIban(account.getIban())).willReturn(account);
        given(accountService.accountExist(account.getIban())).willReturn(true);
        this.mvc.perform(
                put("/accounts/{iban}","NL01INHO0000000002")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\n" +
                                "  \"absoluteLimit\": 0,\n" +
                                "  \"status\": \"Iasdasdase\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "Employee", password = "employee1", roles = "Employee")
    public void EmployeeUpdateAccountWithWrongIbanAndCorrectBodyShouldReturn400() throws Exception {
        given(accountService.getAccountByIban(account.getIban())).willReturn(account);
        given(accountService.accountExist(account.getIban())).willReturn(true);
        this.mvc.perform(
                put("/accounts/{iban}","NL01INHO0000000005")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\n" +
                                "  \"absoluteLimit\": 0,\n" +
                                "  \"status\": \"Inactive\"\n" +
                                "}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "Customer", password = "Customer1", roles = "Customer")
    public void CustomerUpdateAccountShouldReturnForbidden403() throws Exception {
        given(accountService.getAccountByIban(account.getIban())).willReturn(account);
        given(accountService.accountExist(account.getIban())).willReturn(true);
        this.mvc.perform(
                put("/accounts/{iban}","NL01INHO0000000002")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\n" +
                                "  \"absoluteLimit\": 0,\n" +
                                "  \"status\": \"Inactive\"\n" +
                                "}"))
                .andExpect(status().isForbidden());
    }


    //endregion

}
