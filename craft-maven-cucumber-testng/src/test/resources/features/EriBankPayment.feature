Feature: Eribank Application

  @Mobile
  Scenario: Login to EriBank
    Given I launch eribank
    When I enter "company" and "company"
    Then I click LOGIN
    And I click LOGOUT

  @Mobile
  Scenario Outline: MakePayment
    Given I launch eribank
    When I enter "company" and "company"
    Then I click LOGIN
    When I click Make Payment
    And I enter Phone number as "<phone>" name as "<name>" Amount as "<amount>"
    Then I select country as "<country>"
    And click Send Payment
    And I click LOGOUT

    Examples: 
      | phone      | name  | amount | country |
      | 9876543210 | John  |  10000 | India   |
      |     123456 | Harry |  10000 | USA     |
