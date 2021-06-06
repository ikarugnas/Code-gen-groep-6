package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.swagger.model.LoginDTO;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class LoginUserSteps {

    HttpHeaders headers = new HttpHeaders();
    String baseURL = "http://localhost:8080/users/";
    RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    ObjectMapper mapper = new ObjectMapper();

    @When("I log in with username {string} and password {string}")
    public void ILogInWithCorrectCredentials(String username, String password) throws URISyntaxException, JsonProcessingException {
        LoginDTO loginDTO = new LoginDTO(username, password);
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(baseURL + "login");
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(loginDTO), headers);
        try {
            responseEntity = template.postForEntity(uri, entity, String.class);
        } catch (HttpClientErrorException exception) {
            responseEntity = new ResponseEntity<String>(exception.getResponseBodyAsString(), exception.getStatusCode());
        }
    }

    @Then("I get status {int} from post \\/users\\/login")
    public void IGetStatus(int status){
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(status, response);

    }

    @Then("I get body {string} from post \\/users\\/login")
    public void IGetBody(String error){
        Assert.assertEquals(error, responseEntity.getBody());
    }
}
