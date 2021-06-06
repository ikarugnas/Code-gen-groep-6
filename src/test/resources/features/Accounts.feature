Feature: Account Test
# voor het runnen van deze testen moet er eerst code gecomment worden. REGEL 87
#werkt
  Scenario: Employee ophalen van alle accounts is een lijst
    When Employee log in
    Then Ik alle accounts ophaal
    And Krijg een lijst terug van accounts
    And Is de status van het request 200

#werkt
  Scenario: Employee een Account ophalen met een correcte iban
    When Employee log in
    Then Ik een Account ophaal met iban "NL01INHO0000000002"
    And Is het account absoluteLimit 0.0
    And Is de status van het request 200

#werkt
  Scenario: Employee een Account ophalen met een correcte username
    When Employee log in
    Then Ik een Account ophaal met Username "test1"
    And Is het iban van het account "NL01INHO0000000002"
    And Is de status van het request 200

#werkt niet... krijg [no body] maar error code is wel juist.
  Scenario: Employee een Account ophaal met een verkeerde username
    When Employee log in
    Then Ik een Account ophaal met Username "test"
    And Is de status van het request 404

  #werkt
  Scenario: Employee een Account aanmaken
    When Employee log in
    Then Ik een Account maak met AbsoluteLimit 100.00 en owner "test1" en type "Current" en active Status.Active
    And Is de status van het request 201

#werkt niet
  Scenario: Employee update account voor rood staan
    When Employee log in
    Then Update account "NL01INHO0000000002" rood staan absoluteLimit -100.0
#    And check if account "NL01INHO0000000002" absolutelimit -100.0 is updated