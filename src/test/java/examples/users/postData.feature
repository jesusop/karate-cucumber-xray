Feature: User Creation API Testing

  Background:
    * url 'https://reqres.in/api'

  @KAN-8 @regression-test
  Scenario: Create a new user with valid data
    Given path '/users'
    And request { "name": "John Doe", "job": "Tester" }
    When method POST
    Then status 201
    And match response.name == 'John Doe'

  @KAN-9 @regression-test
  Scenario: Update user data
    Given path '/users/2'
    And request {"name": "morpheus","job": "zion resident"}
    When method PUT
    Then status 200
    And match response.name == 'morpheus'