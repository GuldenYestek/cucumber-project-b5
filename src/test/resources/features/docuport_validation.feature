@docuport_validation
Feature: Validation of Navigation Items of Docuport

  Scenario Outline: verify each navigation item
    Given user is on Docuport login page
    When user enters username for "<role>"
    And user enters password for "<role>"
    And user clicks login button
    Then user should be able to see the home page for "<role>"
    And user should see the following "<items>"

    Examples:
      | role       | items                                                                                                        |
      | client     | Home, Received docs, My uploads, Invitations                                                                 |
      | advisor    | Home, Received docs, My uploads, Clients, Invitations, Users, Leads, Bookkeeping, 1099 Form, Reconciliations |
      | supervisor | Home, Received docs, My uploads, Clients, Users, Leads, Bookkeeping, 1099 Form, Reconciliations              |
      | employee   | Home, Received docs, My uploads, Clients, Users, Bookkeeping, 1099 Form, Reconciliations                     |
