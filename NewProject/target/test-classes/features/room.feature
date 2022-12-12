@room
Feature: get room id of workspace
  Scenario: Check api get room
    When I get information of rooms in my workspace
    Then Check response code when get room's info