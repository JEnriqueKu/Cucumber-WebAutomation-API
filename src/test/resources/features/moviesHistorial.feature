Feature: Check History page on Wikipedia
  Scenario: Check Wikipedia History pages of Star Wars Movies
    Given I am an user at the Wikipedia WebPage requesting a random Star Wars movie
    When I search the requested movie name on Wikipedia
    And I click on the History button
    Then I should be able to see the history page correctly displayed for the requested movie