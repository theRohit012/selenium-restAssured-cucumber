Feature: Validate Schema of an Application

  Scenario Outline: Validate schema for users
    Given I have a "@baseURL"
    Then I call "<API>" with "<RequestType>" request to verify the "<SchemasFile>" JSON schema

    Examples:
      | API        | RequestType | SchemasFile          |
      | /api/users | GET         | usersListSchema.json |
