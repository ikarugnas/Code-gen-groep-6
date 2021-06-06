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

    @When("Ik een Account ophaal met iban {string}")
    public AccountWithTransactions ikEenAccountOphaalMetIban(String iban) throws URISyntaxException {
        URI uri = new URI(baseURL + iban);
        HttpEntity httpEntity = new HttpEntity<>(null, headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        return template.exchange(uri, HttpMethod.GET, httpEntity, AccountWithTransactions.class).getBody();

    }

    @Then("Is het username van het eigenaar {string}")
    public void isHetUsernameVanHetEigenaar(String username) throws JSONException {
//        responseEntity.getBody();
//        JSONObject jsonObject = new JSONObject(username);
//        AccountWithTransactions expected = accountService.getAccountByIban("NL01INHO0000000002");
//        JSONArray expectedUsear = jsonObject.getJSONArray("owner");
//        System.out.println(expectedUsear.getString(0));;
////        User expectedUser = expected.getOwner();
////        Assert.assertArrayEquals(username, jsonObject.getString(""));
    }

    @When("Ik een Account ophaal met Username {string}")
    public void ikEenAccountOphaalMetUsername(String username) throws URISyntaxException {
        URI uri = new URI(baseURL + "username/" + username);
        responseEntity = template.getForEntity(uri, String.class);
    }
}

