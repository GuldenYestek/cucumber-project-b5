Feature: Scenario Outline Practice

  @wikipedia_search_outline
  Scenario Outline:
    Given user is on Wikipedia home page
    When user types "<name>" in the wiki search box
    Then user clicks wiki search button
    And user sees "<name>" is in the "<title>"

    @wikipedia_title
    Examples:
      | name       | title      |
      | Steve Jobs | wiki title |

    @wikipedia_main_header
    Examples:
      | name       | title       |
      | Steve Jobs | main header |

    @wikipedia_image_header
    Examples:
      | name       | title        |
      | Steve Jobs | image header |


