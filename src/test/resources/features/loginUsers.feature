Feature: loginUsers test

  Scenario: Login User gives status 200
    When I log in with correct credentials
    Then I get status 200