@Regression
Feature: Book flight tickets
  As a user with valid credentials,
  I want to be able to search for flight tickets between a given source and destination,
  select from the available options presented, and book the tickets accordingly

  Background: 
    Given I am in the login page of the application
    And I login using the valid username mercury and the valid password mercury

  @Regression
  Scenario: Find and book flight tickets
    Given I search for flights using the following criteria:
      | FromPort | FromMonth | FromDate | ToPort | ToMonth | ToDate | Airline          | PassengerCount |
      | London   | July      |       23 | Paris  | July    |     29 | Unified Airlines |              2 |
    And I select the first available flight
    And I book the tickets using the following passenger details:
      | FirstName | LastName   |
      | Cts       | CoE        |
      | QA        | Automation |
    And I use the following credit card details:
      | CreditCardType | CreditCardNumber |
      | MasterCard     |       1234567890 |
    Then I should get a booking confirmation with a confirmation number
