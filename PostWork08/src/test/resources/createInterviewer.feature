Feature: Validate insert a new interviewer

  Scenario: Insert with all data
    Given the name "Francisco" last name "Camas", email "francisco_camas@hotmail.com", is admin "yes" and is active "Yes"
    When the admin user request to insert a new interviewer
    Then the system should insert the data of the new interviewer and return a Id