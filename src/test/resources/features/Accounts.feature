Feature: Account Test

#werkt
  Scenario: Ophalen van alle accounts geeft status 200
    When Ik alle accounts ophaal
    Then Is de status van het request 200

#werkt
  Scenario: Ophalen van alle accounts is een lijst
    When Ik alle accounts ophaal
    Then Krijg een lijst terug van accounts
    And Is de status van het request 200

#werkt
  Scenario: een Account ophaal met iban
    When Ik een Account ophaal met iban "NL01INHO0000000001"
    Then Is het account absoluteLimit 1000.0
    And Is de status van het request 200

#werkt
  Scenario: een Account ophaal met username
    When Ik een Account ophaal met Username "bank"
    Then Is het iban van het account "NL01INHO0000000001"
    And Is de status van het request 200