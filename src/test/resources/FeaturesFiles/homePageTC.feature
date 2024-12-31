Feature: Verify homePage functionalities

  Scenario: Verify Application Logo
    Given I am launching a browser: "@browserName" in mode: "@runMode" and opening an "@url" url
    Then Verify "homePageLogo" element is displayed on "HomePageOR"