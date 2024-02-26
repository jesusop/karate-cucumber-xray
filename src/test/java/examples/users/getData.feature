Feature: User Management API Testing

  Background:
    * url 'https://reqres.in/api'

  @KAN-7 @regression-test
  Scenario: Get a list of users
    Given path '/users'
    When method GET
    Then status 200
    And match response contains { data: '#notnull' }
