@tag
Feature: Tlogin as user

  @tag1
  Scenario Outline: logging in with correct credentials
    Given the user is on the Home page and login
    When the user enters "<username>" and "<password>" to log in
    And the user clicks the login button
    Then the navbar contains "<username>"

    Examples: 
      | username | password |
      | name1    | success  |
      | name2    | success  |

      @tag2 
    Scenario Outline: logging in with incorrect passwords
    Given the user is on the home page and login
    And the user clicks the login link
    When the user enters "<username>" and "<password>" to log in
    And the user clicks the login button
    Then the page says Incorrect Credentials

    Examples: 
      | username  | password |
      | bart      |  p4ss    |
      | homer     |  Pass    |
      | sell      |  1234    |