Feature: check Star Wars Movies on wikipedia
  Scenario: Check wikipedia edit page displayed for Star Wars Movies
    Given I am an user at the Wikipedia WebPage requesting a random SW movie
    When I search the requested movie name on Wikipedia search page
    And I click on the edit button
    Then I should be able to see the edit page correctly displayed for the requested movie
