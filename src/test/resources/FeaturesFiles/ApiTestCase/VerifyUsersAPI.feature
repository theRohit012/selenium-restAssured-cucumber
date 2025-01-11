Feature: Verify /api/users API

  Background:
    Given I have a "@baseURL"

  Scenario Outline: Validate Schema
    Then  I call "<API>" with "<RequestType>" request to verify the "<SchemasFile>" JSON schema

    Examples:
      | API        | RequestType | SchemasFile                      |
      | /api/users | GET         | JsonSchemas/usersListSchema.json |

  Scenario Outline: Validate API response
    When I call "<API>" with "<RequestType>" request
    Examples:
      | API        | RequestType | expectedData                     |
      | /api/users | GET         | JsonSchemas/usersListSchema.json |