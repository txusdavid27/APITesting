Feature: Update the Last Resource

  Scenario: Update the last resource
    Given there are registered resources in the system
    And I retrieve the details of the latest resource
    When I send a PUT request to update the latest resource
    """
    {
      "name": "SrBuñuelo",
      "trademark": "TraslaviñaSA",
      "stock": 159589,
      "price": 17777.07,
      "description": "Designed to keeping in mind durability as well as trends",
      "tags": "status",
      "is_active": false
    }
    """
    Then the resource response should have a status code of 200
    And the resource response should have the following details:
      | name        | trademark         | stock   | price    | description                           | tags   | is_active |
      | SrBuñuelo  | TraslaviñaSA    | 159589    | 17777.07    | Designed to keeping in mind durability as well as trends             | status | false    |
    And validates the response with the resource JSON schema
