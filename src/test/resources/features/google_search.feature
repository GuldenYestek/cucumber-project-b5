@google_search @smoke
Feature: Google Search Functionality Title Validation
  User Story: As a user, when I am on the Google search page
  I should be able to search whatever I want and see the relevant result

  @smoke
  Scenario: Search functionality result title validation
    Given user is on Google search page
    When user types Loop Academy in the google search box and clicks enter
    Then user should be able to see Loop Academy - Google search in the google title


  @smoke
  Scenario: Search functionality result title validation
    Given user is on Google search page
    When user types "Loop Academy" in the google search box and clicks enter
    Then user should be able to see "Loop Academy - Google Search" in the google title

  @smoke
  Scenario: Search functionality result title validation
    Given user is on Google search page
    When user types "Gulden" in the google search box and clicks enter
    Then user should be able to see "Gulden - Google Search" in the google title
