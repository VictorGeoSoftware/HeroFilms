Feature: First start
  Load app for first time, and show up movies list

  Scenario: Load app and retrieve Thor movies list
    Given a user launch the app for first time
    When main view screen is shown
    And movies list is shown
    And featured movies list is shown
    Then both of movies list are fulfilled


