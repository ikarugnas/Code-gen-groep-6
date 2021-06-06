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
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class LoginUserSteps {

    HttpHeaders headers = new HttpHeaders();
    String baseURL = "http://localhost:8080/users/";
    RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    ObjectMapper mapper = new ObjectMapper();

    @When("I log in with correct credentials")
    public void ILogInWithCorrectCredentials() throws URISyntaxException, JsonProcessingException {
        LoginDTO loginDTO = new LoginDTO("employee", "hoi");
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(baseURL + "login");
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(loginDTO), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I log in with wrong password")
    public void ILogInWithWrongPassword() throws URISyntaxException, JsonProcessingException {
        LoginDTO loginDTO = new LoginDTO("employee", "hoi2");
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(baseURL + "login");
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(loginDTO), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @Then("I get status {int}")
    public void IGetStatus(int status){
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(status, response);

    }

    @And("I get body {string}")
    public void IGetBody(String error){
        Assert.assertEquals(error, responseEntity.getBody());
    }
}
