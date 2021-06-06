Feature: loginUsers test

#  Scenario: Login User with valid credentials gives status 200 and String
#    When I log in with username "employee" and password "hoi"
#    Then I get status 200 from post /users/login
#
#  Scenario: Login with wrong password gives status 422 and error "Username or Password is incorrect"
#    When I log in with username "employee" and password "hoi2"
#    Then I get status 422 from post /users/login
#    And I get body "Username or Password is incorrect" from post /users/login
#
#  Scenario: Login with wrong username gives status 422 and error "Username or Password is incorrect"
#    When I log in with username "hoi" and password "hoi"
#    Then I get status 422 from post /users/login
#    And I get body "Username or Password is incorrect" from post /users/login
#
#  Scenario: Login inactive user gives status 422 and error "User account is inactive, please take contact with an employee!"
#    When I log in with username "inactive" and password "hoi"
#    Then I get status 422 from post /users/login
#    And I get body "User account is inactive, please take contact with an employee!" from post /users/login