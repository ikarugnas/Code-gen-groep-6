package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.LoginDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class getTransactionsByIbanSteps {

    HttpHeaders headers = new HttpHeaders();
    String baseURL = "http://localhost:8080/transactions/NL01INHO0000000001";
    RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    ObjectMapper mapper = new ObjectMapper();

    public Timestamp convertToTimestamp(String date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormatWithHours = new SimpleDateFormat("dd/MM/yyyy HH");

        try{
            if (date.contains(" ")) {
                return new Timestamp(dateFormatWithHours.parse(date).getTime());
            } else {
                return new Timestamp(dateFormat.parse(date).getTime());
            }
        } catch (ParseException parseException) {
            throw new Exception("Date has an invalid format");
        }
    }

    public void login() throws JsonProcessingException, URISyntaxException {
        LoginDTO loginDTO = new LoginDTO("employee", "hoi");
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("http://localhost:8080/users/login");
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(loginDTO), headers);
        try {
            responseEntity = template.postForEntity(uri, entity, String.class);
        } catch (HttpClientErrorException exception) {
            responseEntity = new ResponseEntity<String>(exception.getResponseBodyAsString(), exception.getStatusCode());
        }
        headers.add("Authorization", "Bearer " + responseEntity.getBody());
    }

    @When("I get transactions by iban")
    public void IGetTransactionsByIban() throws URISyntaxException, JsonProcessingException {
        login();

        URI uri = new URI(baseURL);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @When("I get transactions by iban with {string} is {int}")
    public void IGetTransactionsByIbanWithIntParameterIs(String parameter, int value) throws URISyntaxException, JsonProcessingException {
        login();

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(String.valueOf(baseURL))
                .queryParam(parameter, value);

        responseEntity = template.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, String.class);

    }

    @When("I get transactions by iban with {string} is {string}")
    public void IGetTransactionsByIbanWithStringParameterIs(String parameter, String value) throws URISyntaxException, JsonProcessingException {
        login();

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(String.valueOf(baseURL))
                .queryParam(parameter, value);

        responseEntity = template.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, String.class);

    }

    @Then("I get status {int} from get \\/transactions\\/iban")
    public void IGetStatus(int status){
        Assert.assertEquals(status, responseEntity.getStatusCodeValue());
    }

    @Then("I get array from get \\/transactions\\/iban")
    public void IGetArray() throws JSONException {
        JSONArray jsonArray = new JSONArray(responseEntity.getBody());
        Assert.assertTrue(jsonArray.length() > 1);
    }

    @Then("I get array with length of {int} from \\/transactions\\/iban")
    public void IGetArrayWithLengthOf(int length) throws JSONException {
        JSONArray jsonArray = new JSONArray(responseEntity.getBody());
        Assert.assertEquals(length, jsonArray.length());
    }

    @Then("I get every object with transactionType of transaction from \\/transaction\\/iban")
    public void IGetEveryObjectWithTransactionTypeOfTransaction() throws JSONException {
        JSONArray jsonArray = new JSONArray(responseEntity.getBody());
        for (int i = 0; i < jsonArray.length(); i++) {
            Assert.assertEquals("Transaction", jsonArray.getJSONObject(i).getString("transactionType"));
        }
    }

    @Then("I get every object with dateAndTime must be younger than {string} from \\/transaction\\/iban")
    public void IGetEveryObjectWithDateFromMustBeYoungerThan(String dateFrom) throws Exception {
        Timestamp timestampExpected = convertToTimestamp(dateFrom);
        Timestamp timestampActual;

        JSONArray jsonArray = new JSONArray(responseEntity.getBody());
        for (int i = 0; i < jsonArray.length(); i++) {
            timestampActual = Timestamp.valueOf(jsonArray.getJSONObject(i).getString("dateAndTime").replace('T', ' ').substring(0, 19));
            Assert.assertTrue(timestampActual.after(timestampExpected) || timestampActual.equals(timestampExpected));
        }
    }

    @Then("I get every object with dateAndTime must be older than {string} from \\/transaction\\/iban")
    public void IGetEveryObjectWithDateToMustBeOlderThan(String dateTo) throws Exception {
        Timestamp timestampExpected = convertToTimestamp(dateTo);
        Timestamp timestampActual;

        JSONArray jsonArray = new JSONArray(responseEntity.getBody());
        for (int i = 0; i < jsonArray.length(); i++) {
            timestampActual = Timestamp.valueOf(jsonArray.getJSONObject(i).getString("dateAndTime").replace('T', ' ').substring(0, 19));
            Assert.assertTrue(timestampActual.before(timestampExpected) || timestampActual.equals(timestampExpected));
        }
    }
}
