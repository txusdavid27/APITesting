package com.testing.api.stepDefinitions;

import com.google.gson.Gson;
import com.testing.api.models.Resource;
import com.testing.api.requests.ResourceRequest;
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

public class ResourceSteps {
    private static final Logger logger = LogManager.getLogger(ResourceSteps.class);
    private ResourceRequest resourceRequest= new ResourceRequest();
    private Response response;
    private Resource createdResource;
    private JsonFileReader jsonFileReader = new JsonFileReader();

    Integer lastR;

    @Given("there are registered resources in the system")
    public void thereAreRegisteredResourcesInTheSystem() {
        response = resourceRequest.getResources();
        logger.info("Retrieved the list of resources with status code: " + response.statusCode());
    }

    @Given("I have a resource with the following details:")
    public void iHaveAResourceWithTheFollowingDetails(DataTable resourceData) {
        List<Map<String, String>> data = resourceData.asMaps(String.class, String.class);
        Map<String, String> resourceDetails = data.get(0);

        createdResource = Resource.builder()
            .name(resourceDetails.get("Name"))
            .trademark(resourceDetails.get("Trademark"))
            .stock(Integer.parseInt(resourceDetails.get("Stock")))
            .price(Double.parseDouble(resourceDetails.get("Price")))
            .description(resourceDetails.get("Description"))
            .tags(resourceDetails.get("Tags"))
            .is_active(Boolean.parseBoolean(resourceDetails.get("Is_active")))
            .build();

        logger.info("Created a new resource with details: " + createdResource.toString());
    }

    @When("I retrieve the details of the resource with ID {string}")
    public void retrieveResourceDetails(String resourceId) {
        response = resourceRequest.getResource(resourceId);
        logger.info("Retrieved resource details for ID " + resourceId + " with status code: " + response.statusCode());
    }

    @When("I retrieve the details of the latest resource")
    public void retrieveLatestResourceDetails() {
        Response response = resourceRequest.getResources(); // Obtener el Response
        Integer elementCount = resourceRequest.countElementsInResponse(response, "resources"); // Contar elementos en la lista "resources"
        System.out.println("Cantidad de elementos: " + elementCount);
        lastR=elementCount--;
        response = resourceRequest.getResource((lastR).toString());
        logger.info("Retrieved resource details for ID " + elementCount + " with status code: " + response.statusCode());
    }
    @When("I send a GET request to view all the resources")
    public void sendGETRequestToViewAllResources() {
        response = resourceRequest.getResources();
        logger.info("Sent a GET request to view all resources with status code: " + response.statusCode());
    }

    @When("I send a PUT request to update the latest resource")
    public void sendPUTRequestToUpdateLatestResource(String requestBody) {
        Resource updatedResource = new Gson().fromJson(requestBody, Resource.class);
        response = resourceRequest.updateResource(updatedResource, lastR.toString());
        logger.info("Sent a PUT request to update the latest resource with status code: " + response.statusCode());
    }


    @Then("the resource response should have a status code of {int}")
    public void validateResourceResponseStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
        logger.info("Response status code matches expected: " + expectedStatusCode);
    }

    @Then("the resource response should have the following details:")
    public void validateResourceResponseDetails(DataTable expectedData) {
        logger.info("Validating response details with expected data: " + expectedData);

        String responseBody = response.getBody().asString();

        Resource responseResource = jsonFileReader.getResourceByJson(responseBody);

        List<Map<String, String>> data = expectedData.asMaps(String.class, String.class);
        Map<String, String> expectedResourceDetails = data.get(0);
        Resource expectedResource = Resource.builder()
                .name(expectedResourceDetails.get("name")) // Use lowercase
                .trademark(expectedResourceDetails.get("trademark"))
                .stock(Integer.parseInt(expectedResourceDetails.get("stock")))
                .price(Double.parseDouble(expectedResourceDetails.get("price")))
                .description(expectedResourceDetails.get("description"))
                .tags(expectedResourceDetails.get("tags"))
                .is_active(Boolean.parseBoolean(expectedResourceDetails.get("is_active")))
                .build();

        Assert.assertEquals(expectedResource.getName(), responseResource.getName());
        Assert.assertEquals(expectedResource.getTrademark(), responseResource.getTrademark());
        Assert.assertEquals(expectedResource.getStock(), responseResource.getStock());
        Assert.assertEquals(expectedResource.getPrice(), responseResource.getPrice(), 0.001); // Comparación de valores decimales con tolerancia
        Assert.assertEquals(expectedResource.getDescription(), responseResource.getDescription());
        Assert.assertEquals(expectedResource.getTags(), responseResource.getTags());
        Assert.assertEquals(expectedResource.is_active(), responseResource.is_active());

    }


    @Then("validates the response with the resource JSON schema")
    public void validateResponseWithResourceJSONSchema() {
        logger.info("Validating response with resource JSON schema");
        boolean schemaValidationResult = resourceRequest.validateSchema(response, "schemas/resourceSchema.json");
        Assert.assertTrue(schemaValidationResult);
    }

    @Then("validates the response with the resource list JSON schema")
    public void validateResponseWithResourceListJSONSchema() {
        logger.info("Validating response with resource JSON schema");
        boolean schemaValidationResult = resourceRequest.validateSchema(response, "schemas/resourceListSchema.json");
        Assert.assertTrue(schemaValidationResult);
    }

}
