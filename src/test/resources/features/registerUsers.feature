Feature: registerUsers test

#  Scenario: Register user with username, password, name and email that not already exists gives status 422 and user object
#    When I log in user with employee role
#    And I register user with username "Jdoe", password "gH^xD81b", name "john doe" and email "John@doe.nl"
#    Then I get status 201 from post /users
#
#  Scenario: Register user with username that already exists gives status 422 and error string
#    When I log in user with employee role
#    And I register user with username "employee", password "hoi", name "john doe" and email "John@doe.nl"
#    Then I get status 422 from post /users
#    And I get body "Username already exists" from post /users