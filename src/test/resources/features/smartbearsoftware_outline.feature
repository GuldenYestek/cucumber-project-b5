Feature: Place an order from smartbearsoftware

  @smartbearsoftware
  Scenario Outline: user should be able to place order and order should be seen in page
    Given user is already logged in and navigated to order page
    When user selects product type "<product>"
    And user enters quantity <qty>
    And user enters customer name "<name>"
    And user enters street "<street>"
    And user enters city "<city>"
    And user enters state "<state>"
    And user enters zip "<zip>"
    And user selects credit card type "<cardType>"
    And user enters credit car number "<cardNumber>"
    And user enters expiration date "<exp>"
    And user enters process order button
    Then user should see "<name>" in the first row of the table

    @americanExpress
    Examples:
      | product     | qty | name         | street           | city      | state    | zip   | cardType         | cardNumber       | exp   |
      | FamilyAlbum | 2   | Chuck Norris | 1100 Long way dr | Chantilly | Virginia | 22011 | American Express | 1111222233334444 | 12/25 |

    @visa
    Examples:
      | product | qty | name        | street    | city   | state | zip   | cardType | cardNumber       | exp   |
      | MyMoney | 1   | Ben Affleck | 1 Main St | Austin | TX    | 73301 | Visa     | 4111111111111111 | 01/27 |

    @mastercard
    Examples:
      | product     | qty | name         | street        | city    | state | zip   | cardType   | cardNumber       | exp   |
      | ScreenSaver | 3   | Leo Dicaprio | 99 Center Ave | Seattle | WA    | 98101 | MasterCard | 5555555555554444 | 06/26 |
