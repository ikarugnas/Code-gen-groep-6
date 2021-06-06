Feature: loginUsers test

  Scenario: Login User gives status 200
    When I log in with correct credentials
    Then I get status 200

  Scenario: Login with wrong password gives status 422 and error
    When I log in with wrong password
    Then I get status 422