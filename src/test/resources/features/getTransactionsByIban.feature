Feature: get transactions by iban

  Scenario: get transactions gives status 200 and array
    When I get transactions by iban
    Then I get status 200 from get /transactions/Iban
    And I get array from get /transactions/iban

  Scenario: get transaction gives status 200 and 1 object when limit is 1
    When I get transactions by iban with "limit" is 1
    Then I get status 200 from get /transactions/iban
    And I get array with length of 1 from /transactions/iban

  Scenario: get transaction gives status 200 and every transaction is of type transaction when transactionType is transaction
    When I get transactions by iban with "transactionType" is "transaction"
    Then I get status 200 from get /transactions/iban
    And I get every object with transactionType of transaction from /transaction/iban

  Scenario: get transaction gives status 200 and every transaction is after dateFrom when dateFrom is 15/11/2020
    When I get transactions by iban with "dateFrom" is "15/11/2020"
    Then I get status 200 from get /transactions/iban
    And I get every object with dateAndTime must be younger than "15/11/2020" from /transaction/iban

  Scenario: get transaction gives status 200 and every transaction is before dateTo when dateTo is 14/11/2020
    When I get transactions by iban with "dateTo" is "14/11/2020"
    Then I get status 200 from get /transactions/iban
    And I get every object with dateAndTime must be older than "14/11/2020" from /transaction/iban