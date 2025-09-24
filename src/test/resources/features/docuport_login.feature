Feature: Scenario Outline Practice

  @docuport_login @smoke
  Scenario Outline:
    Given user is on Google search page
    When user searches for "<country>"
    Then user should see "<capital>" in the results as capital
    And we love Loop Academy

    Examples:
      | country     | capital    |
      | Azerbaijan  | Baku       |
      | Kazakhstan  | Astana     |
      | Afghanistan | Kabul      |
      | USA         | Washington |
      | Turkiye     | Ankara     |
      | Uzbekistan  | Tashkent   |
      | Georgia     | Tbilisi    |




      # option + command + L  - mac
      # CTRL + ALT + L        - window