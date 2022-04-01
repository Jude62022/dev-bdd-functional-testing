Feature: Request all records

Scenario: Request all records
Given appointment records already exists
When all appointments are requested
Then it should return all available appointments