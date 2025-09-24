Feature: Docuport Login Logout Feature

  Background: this is for navigating Docuport login page
    Given user is on Docuport login page




  Scenario: Login as a client
    #Given user is on Docuport login page
    When user enters username for client
    And user enters password for client
    And user clicks login button
    Then user should be able to see the home page for client




  Scenario: Login as a employee
   # Given user is on Docuport login page
    When user enters username for employee
    And user enters password for employee
    And user clicks login button
    Then user should be able to see the home page for employee


  Scenario: Login as a advisor
    #Given user is on Docuport login page
    When user enters username for advisor
    And user enters password for advisor
    And user clicks login button
    Then user should be able to see the home page for advisor


  Scenario: Login as a supervisor
    #Given user is on Docuport login page
    When user enters username for supervisor
    And user enters password for supervisor
    And user clicks login button
    Then user should be able to see the home page for supervisor


  @dataTableLogin
  Scenario: Login as a client map practice
    When user enters credentials
      | username | b1g1_client@gmail.com |
      | password | Group1                |
    Then user should be able to see the home page for client



















