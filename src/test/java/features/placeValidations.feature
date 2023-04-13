#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: vailidating Place API
 

  @Regression @AddPlace
  Scenario Outline: Verify Place added successsfully using Add Place API
    Given Add place payload "<name>" "<language>"
    When User calls "addPlaceAPI" with "post" http request
    Then API call success with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify the placeId is the map with "<name>" using "getPlaceAPI"
    
    Examples:
    |name    |language|
    |Yashika Puneet M | Hindi |
   # |Ivyaan M| Dutch|
   
   
   @UpdatePlace @Regression
   Scenario Outline: Verify place updated successfully using Update Place API
   Given Add update place payload "<address>" "<key>"
   When User calls "updatePlaceAPI" with "put" http request
   Then API call success with status code "200"
   Then verify the response message "msg"
  
Examples:
|address|key|
|86 Sommer walk,India|qaclick123|