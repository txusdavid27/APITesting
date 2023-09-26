Feature: Create a New Client

  Scenario: Create a new client
    Given I have a client with the following details:
      | Name    | LastName   | Country  | City   | Email                | Phone          |
      | Alice   | Johnson    | USA      | NYC    | alice@email.com      | 123-456-7890   |
    When I send a POST request to create a client
    Then the client response should have a status code of 201
    And the response should include the details of the created client
    And validates the response with the client JSON schema
