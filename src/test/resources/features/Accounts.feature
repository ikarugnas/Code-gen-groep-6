Feature: Account Test

  Scenario: Ophalen van alle accounts geeft status 200
    When Ik alle accounts ophaal
    Then Is de status van het request 200

  Scenario: een Account ophaal met iban
    When Ik een Account ophaal met iban "NL01INHO0000000002"
    Then Is het username van het eigenaar "test1"

  Scenario: een Account ophaal met username
    When Ik een Account ophaal met Username "test1"
    Then Is het username van het eigenaar "test1"