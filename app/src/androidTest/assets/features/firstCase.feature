Feature: First start
  Load app for first time, and show up movies list

  @firstCase
  Scenario: Load app, retrieve Thor movies list and select one form the movies list
    Given a user launch the app for first time
    When main view screen is shown
    And both of movies list are fulfilled
    And a movie from movies list is selected
    Then I see the detail activity with all movie info


