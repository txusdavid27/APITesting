package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import com.testing.api.utils.JsonFileReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ClientSteps {
    private static final Logger logger = LogManager.getLogger(ClientSteps.class);
    private ClientRequest clientRequest= new ClientRequest();
    private Response response;
    private Client createdClient;

    @Given("there are registered clients in the system")
    public void thereAreRegisteredClientsInTheSystem() {
        response = clientRequest.getClients();
        logger.info("Retrieved the list of clients with status code: " + response.statusCode());
    }

    @Given("I have a client with the following details:")
    public void iHaveAClientWithTheFollowingDetails(DataTable clientData) {

        List<Map<String, String>> data = clientData.asMaps(String.class, String.class);
        Map<String, String> clientDetails = data.get(0);

        createdClient = new Client(
            clientDetails.get("Name"),
            clientDetails.get("LastName"),
            clientDetails.get("Country"),
            clientDetails.get("City"),
            clientDetails.get("Email"),
            clientDetails.get("Phone"),
            null
        );

        logger.info("Created a new client with details: " + createdClient);

    }

    @When("I send a GET request to view all the clients")
    public void sendGETRequestToViewAllClients() {

        response = clientRequest.getClients();
        logger.info("Sent a GET request to view all clients with status code: " + response.statusCode());

    }

    @When("I send a POST request to create a client")
    public void sendPOSTRequestToCreateClient() {
        response = clientRequest.createClient(createdClient);
        logger.info("Sent a POST request to create a client with status code: " + response.statusCode());

    }

    @Then("the client response should have a status code of {int}")
    public void validateClientResponseStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
        logger.info("Response status code matches expected: " + expectedStatusCode);
    }


    @Then("the response should include the details of the created client")
    public void validateResponseIncludesCreatedClientDetails() {
        logger.info("Validating response includes details of the created client");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("id"));
        Assert.assertTrue(responseBody.contains("name"));
    }

    @Then("validates the response with the client JSON schema")
    public void validateResponseWithClientJSONSchema() {
        logger.info("Validating response with client JSON schema");
        boolean schemaValidationResult = clientRequest.validateSchema(response, "schemas/clientSchema.json");
        Assert.assertTrue(schemaValidationResult);
    }

    @Then("validates the response with the client list JSON schema")
    public void validateResponseWithClientListJSONSchema() {
        logger.info("Validating response with client JSON schema");
        boolean schemaValidationResult = clientRequest.validateSchema(response, "schemas/clientListSchema.json");
        Assert.assertTrue(schemaValidationResult);
  }
}
