Feature: Tests to make actuator is configured

Background:
  * url appUrl

Scenario: Check info actuator
  * configure retry = { count: 5, interval: 10 }
  Given path 'actuator/info'
  And retry until responseStatus == 200
  When method get
  Then status 200
  And match $.build.artifact == "sample-app-api"