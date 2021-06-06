package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.configuration.WebSecurityConfig;
import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class registerUserSteps {

    HttpHeaders headers = new HttpHeaders();
    String baseURL = "http://localhost:8080/users/";
    RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    ObjectMapper mapper = new ObjectMapper();

    @When("I log in user with employee role")
    public void ILogInUserWithEmployeeRole() throws URISyntaxException, JsonProcessingException {
        LoginDTO loginDTO = new LoginDTO("employee", "hoi");
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(baseURL + "login");
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(loginDTO), headers);
        try {
            responseEntity = template.postForEntity(uri, entity, String.class);
        } catch (HttpClientErrorException exception) {
            responseEntity = new ResponseEntity<String>(exception.getResponseBodyAsString(), exception.getStatusCode());
        }
        headers.add("Authorization", "Bearer " + responseEntity.getBody());
    }

    @When("I register user with username {string}, password {string}, name {string} and email {string}")
    public void IRegisterUserWithUsernamePasswordNameEmail(String username, String password, String name, String email) throws URISyntaxException, JsonProcessingException {
        RegisterDTO registerDTO = new RegisterDTO(username, password, name, email);
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(baseURL);
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(registerDTO), headers);
        try {
            responseEntity = template.postForEntity(uri, entity, String.class);
        } catch (HttpClientErrorException exception) {
            responseEntity = new ResponseEntity<String>(exception.getResponseBodyAsString(), exception.getStatusCode());
        }
    }

    @Then("I get status {int} from post \\/users")
    public void IGetStatus(int status){
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(status, response);

    }

    @Then("I get body {string} from post \\/users")
    public void IGetBody(String body){
        Assert.assertEquals(body, responseEntity.getBody());
    }
}
