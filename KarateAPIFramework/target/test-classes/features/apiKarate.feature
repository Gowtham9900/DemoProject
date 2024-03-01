Feature: API Automation testing
 
  Background: 
    * url 'https://restful-booker.herokuapp.com'
    * header Accept = 'application/json'
    * def projectPath = karate.properties['user.dir']
 
  Scenario: Get Booking
    Given path '/booking'
    When method GET
    Then status 200
    And print response
 
  Scenario: Get Booking data
    Given path '/booking/1'
    When method GET
    Then status 200
    And print response
 
  Scenario: POST Create
    Given path '/booking'
    And header Content-type = "application/json"
    And request {"firstname": "Gowtham","lastname": "AR","totalprice": 88,"depositpaid": true,"bookingdates": {"checkin": "2022-01-02","checkout": "2022-01-10"},"additionalneeds": "Coffeebreak"}
    When method POST
    Then status 200
    And print response
    And match response.firstname == 'Gowtham'
  Scenario: PUT Update
    Given path '/booking/1'
    And header Content-type = "application/json"
    And header Cookie = 'token=<token_value>'
    And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
    And request {"firstname": "Dak","lastname": "Gow","totalprice": 17,"depositpaid": false,"bookingdates": {"checkin": "2022-01-1","checkout": "2022-01-4"},"additionalneeds": "test"}
    When method PUT
    Then status 200
    And print response
    And match response.firstname == 'Dak'
    And match response.totalprice == 17
 
  Scenario: PATCH Partially Update
    Given path '/booking/1'
    And header Content-type = "application/json"
    And header Cookie = 'token=<token_value>'
    And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
    And request {"firstname": "LTIM"}
    When method PATCH
    Then status 200
    And print response
    And match response.firstname == 'LTIM'
 
  Scenario: Delete a data
    Given path '/booking/1'
    And header Content-type = "application/json"
    And header Cookie = 'token=<token_value>'
    And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
    When method DELETE
    Then status 201
 