Feature: Verify homePage functionalities

  Background:
    Given I am launching a browser: "@browserName" in mode: "@runMode" and opening an "@url" url

  Scenario: Verify Application Logo
    Then Verify "homePageLogo" element is displayed on "HomePageOR"

  Scenario: Verify Navigation Links in the header
    Then Verify following texts are visible on "HomePageOR"
      | Flight Booking |
      | Top Deals      |