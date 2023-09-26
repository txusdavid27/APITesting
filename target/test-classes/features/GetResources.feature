Feature: Get the List of Resources

  Background:
    Given there are registered resources in the system

  Scenario: Retrieve the list of all resources
    When I send a GET request to view all the resources
    Then the resource response should have a status code of 200
    And validates the response with the resource list JSON schema
