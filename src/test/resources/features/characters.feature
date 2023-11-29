Feature: check Star Wars Characters on wikipedia
  Scenario Outline: Check wikipedia article displayed for Star Wars Characters
    Given I am an user at the Wikipedia WebPage requesting SW character <number>
    When I search the requested character name on Wikipedia search page
    Then I should be able to see the article page correctly displayed for the requested character
    And the side bar should be present on the page
    And the navigation bar should be present on the page
    Examples:
      | number |
      | 1      |
      | 2      |
