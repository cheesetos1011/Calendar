@login
Feature: Check login
  Scenario Outline: Check api login
    When I check email "<userEmail>"
    When I use "<userEmail>" and "<password>" to login
    Then Check status code
    Examples:
      | userEmail | password |
      |jimfisher@coffeetunner.com|abcd1234|