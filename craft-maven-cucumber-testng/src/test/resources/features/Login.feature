Feature: Login
  As a user, I want to be able to login to the application
  when I present valid credentials

  Background: 
    Given I am in the login page of the application

  @Regression
  Scenario Outline: Login with valid username and valid password
    When I login using the valid username <Username> and the valid password <Password>
    Then The application should log me in and navigate to the Flight Finder page

    Examples: 
      | Username | Password |
      | mercury  | mercury  |
      | test     | test     |

  @Regression
  Scenario: Register new user and login using the newly registered credentials
    Given I am in the login page of the application
    When I register a new user with the following details:
      | Username   | cts               |
      | Password   | password-1        |
      | FirstName  | Cognizant         |
      | LastName   | John              |
      | Phone      |        1234567890 |
      | Email      | Cognizant@cts.com |
      | Address    | 90 Matawan Road   |
      | City       | Matawan           |
      | State      | New Jersey        |
      | PostalCode |             07747 |
    Then I should get a confirmation on successful registration
    When I click on the sign in link
    And I login using the valid username icims and the valid password password-1
    Then The application should log me in and navigate to the Flight Finder page
