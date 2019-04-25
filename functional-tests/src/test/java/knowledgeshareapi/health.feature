Feature: Tests to make sure application endpoints work

Background:
  * url appUrl

Scenario: Check hello endpoint
  * configure retry = { count: 5, interval: 10 }
  Given path 'actuator/health'
  And retry until responseStatus == 200
  When method get
  Then status 200