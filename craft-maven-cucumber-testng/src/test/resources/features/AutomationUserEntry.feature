@Completed
Feature: Automation User Entry

  Scenario Outline: Enter Automation Engineer Details
    Given I Launch the Automation Entry Application
    And Verify if home Page is displayed
    Then Enter the Details <FirstName> <LastName> <Country>
    And Submit the Details

    Examples: 
      | FirstName | LastName | Country |
      | John      | Smith    | Europe  |
