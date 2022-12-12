#Get list department, role here
  @orgChart
Feature: Check api organization chart
  Scenario: Get list department
    When I get list department in my workspace
    Then Check response code when get list department
  Scenario: Get list role
    When I get list role in my workspace
    Then Check response code when get list role