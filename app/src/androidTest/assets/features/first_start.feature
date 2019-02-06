Feature: First start
  Load app for first time, and show up movies list

  Background: Background for main view
    Given a user launch the app for first time

  Scenario: Load app, retrieve Thor movies list and select one form the movies list
    When main view screen is shown
    And both of movies list are fulfilled
    And a movie from movies list is selected
    Then I see the detail activity with all movie info

  Scenario: Load app, retrieve Thor movies list and select one from featured movies list
    When main view screen is shown 2
    And both of movies list are fulfilled 2
    And a movie from featured movies list is selected
    Then I see the detail activity with all movie info 2


