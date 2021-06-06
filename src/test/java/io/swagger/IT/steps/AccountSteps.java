package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.LoginDTO;
import io.swagger.model.User;
import io.swagger.service.AccountService;
import io.swagger.service.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class AccountSteps {

    private HttpHeaders headers = new HttpHeaders();
    private String baseURL = "http://localhost:8080/accounts/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private AccountService accountService;
    private UserService userService;

    @When("Ik alle accounts ophaal")
    public void ikAlleAccountsOphaal() throws URISyntaxException {
        URI uri = new URI(baseURL);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    };

    @Then("Is de status van het request {int}")
    public void isDeStatusVanHetRequest(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }

    @Then("Krijg een lijst terug van accounts")
    public void krijgEenLijstTerugVanAccounts() throws JSONException {
        String response = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(response);
        Assert.assertEquals(2, jsonArray.length());

    }

    @When("Ik een Account ophaal met iban {string}")
    public void ikEenAccountOphaalMetIban(String iban) throws URISyntaxException {
        URI uri = new URI(baseURL + iban);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("Is het account absoluteLimit {double}")
    public void isHetAccountAbsoluteLimit(double absoluteLimit) throws JSONException {
        String response = responseEntity.getBody();
        JSONObject jsonObject = new JSONObject(response);
        Assert.assertEquals(absoluteLimit, jsonObject.getDouble("absoluteLimit"), 0);
    }

    @When("Ik een Account ophaal met Username {string}")
    public void ikEenAccountOphaalMetUsername(String username) throws URISyntaxException {
        URI uri = new URI(baseURL + "username/" + username);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("Is het iban van het account {string}")
    public void isHetIbanVanHetAccount(String iban) throws JSONException {
        String response = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(response);
        Assert.assertEquals(iban, jsonArray.getJSONObject(0).getString("iban"));
    }
}

