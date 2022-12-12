@workspace
Feature: Check api workspace
  Scenario: Check api get user's information
    When I get my information to get id of workspace
    Then Check response code when get my information

  Scenario: Check api get all active member in my workspace
    When I get list active member
    Then Check response code when get list active member

