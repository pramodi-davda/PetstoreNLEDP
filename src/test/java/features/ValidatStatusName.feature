Feature: Pet Status

Scenario: Display number of pets with status available and the name doggie

Given RestAssured.baseURI="https://petstore.swagger.io"
    When user finds pets by status
    Then the array size of available pets is returned
    And the number of pets with status available and the name doggie



		
		