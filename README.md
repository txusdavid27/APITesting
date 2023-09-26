# RestAssured Practice- Traslaviña Jesús

This project is a practical exercise involving the implementation of 4 test cases for an API within a development environment. It utilizes the following tools and technologies:

- Java
- Maven
- RestAssured
- POJO (Plain Old Java Object)
- Cucumber (optional)

The primary objectives of these test cases are to ensure flexibility and maintainability, allowing them to adapt seamlessly to potential changes in endpoints or test data. Furthermore, these tests should be capable of running automatically and independently, without encountering errors. Adherence to best coding practices is a priority, which includes providing a README, adding comments, and incorporating JavaDoc documentation when appropriate.

## Test Case 1: Get the List of Clients

**Description:**
Ensure that the list of all clients can be successfully retrieved from the "/api/v1/clients" endpoint.

**Conditions:**
- The "/api/v1/clients" endpoint must exist and be operational.
- A minimum of 3 clients should be present in the system.

**Assertions:**
- Verify that the response status code equals HTTP 200.
- Validate the structure of the response body schema.

## Test Case 2: Get the List of Resources

**Description:**
Confirm that the list of all resources can be successfully retrieved from the "/api/v1/resources" endpoint.

**Conditions:**
- The "/api/v1/resources" endpoint must exist and be operational.
- A minimum of 5 resources should be present in the system.

**Assertions:**
- Verify that the response status code equals HTTP 200.
- Validate the structure of the response body schema.

## Test Case 3: Create a New Client

**Description:**
Ensure that a new client can be successfully created at the "/api/v1/clients" endpoint.

**Conditions:**
- The "/api/v1/clients" endpoint must exist and be operational.
- Data necessary to create a new client is available with the following details:
  - Name: `<name>`
  - Last Name: `<lastName>`
  - Country: `<country>`
  - City: `<city>`
  - Email: `<email@email.com>`
  - Phone: `<123-456-7890>`

**Assertions:**
- Verify that the response status code equals HTTP 201.
- Validate the structure of the response body schema.
- Verify the data within the response body.

## Test Case 4: Update the Last Resource

**Description:**
Confirm that an existing resource can be successfully updated at the "/api/v1/resources" endpoint.

**Conditions:**
- The "/api/v1/resources" endpoint must exist and be operational.
- A minimum of 5 resources should be present in the system.
- Data necessary to update the last resource is as follows:
  - Name: `<NewName>`
  - Brand: `<NewBrand>`
  - Stock: `<1000>`
  - Price: `<99.99>`
  - Description: `<New resource description>`
  - Tags: `<NewTag>`
  - Is_active: `<true>`

**Assertions:**
- Verify that the response status code equals HTTP 200.
- Validate the structure of the response body schema.
- Verify the data within the response body.

Feel free to utilize Cucumber or other testing frameworks to implement these test cases according to your project's requirements. Remember to provide appropriate documentation, comments, and adhere to Java best practices within your codebase.
