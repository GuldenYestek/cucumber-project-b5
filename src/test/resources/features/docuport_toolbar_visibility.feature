@docuport_toolbar_visibility
Feature: Docuport toolbar visibility

  Background:
    Given user is on Docuport login page

  Scenario Outline: "<section>" shows Search and Download for <role>
    When user enters username for "<role>"
    And user enters password for "<role>"
    And user clicks login button
    And the "<role>" opens "<section>" from the side menu
    Then the page header reads "<section>"
    And "Search" button is visible
    And "Download" button is visible

    Examples:
      | role        | section        |
      | advisor     | Received docs  |
      | advisor     | My uploads     |
      | client      | Received docs  |
      | client      | My uploads     |
      | employee    | Received docs  |
      | employee    | My uploads     |
      | supervisor  | Received docs  |
      | supervisor  | My uploads     |
