Feature: First start
  Load app for first time, and show up movies list

  @secondCase
  Scenario: Load app, retrieve Thor movies list and select one from featured movies list
    Given the app launched
    When main view screen shows up
    And both movies and featured movies list are fulfilled
    And a movie from featured movies list is selected
    Then I see detail activity with the complete info


