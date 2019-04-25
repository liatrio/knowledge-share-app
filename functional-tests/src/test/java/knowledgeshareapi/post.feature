# new feature
# Tags: optional
    
Feature: Making and checking a post

Background:
  * url appUrl
    
Scenario: A scenario
    Given path 'post'
    And param firstName = "CoolGuy"
    And request {}
    When method post
    Then status 200
    And match response == {id:'#number', firstName:"CoolGuy", title:null, link:null}
