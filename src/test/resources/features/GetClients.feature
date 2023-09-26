Feature: Get the List of Clients

  Background:
    Given there are registered clients in the system

  Scenario: Retrieve the list of all clients
    When I send a GET request to view all the clients
    Then the client response should have a status code of 200
    And validates the response with the client list JSON schema
