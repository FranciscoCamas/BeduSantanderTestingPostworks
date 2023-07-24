Feature: Validate insert a new interviewer

  Scenario: Insert with all data
    Given the name "Francisco" last name "Camas" and email "francisco_camas@hotmail.com"
    When the admin user request to insert a new interviewer
    Then the system should insert the data of the new interviewer and return a Id