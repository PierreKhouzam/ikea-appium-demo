# Automated Tests for IKEA Application

## Overview

This repository contains automated tests for the IKEA application using Appium Java Client. The tests cover critical functionalities related to product listings, details, and cart management. 

## Features

- **Maven:** managing dependencies using Maven, and the necessary dependencies are automatically resolved and downloaded.
- **Appium Java Client 9.0.0:** Utilizing the latest version of the Appium Java Client to leverage its newest features and improvements.
- **Appium Server Setup:** Includes code for programmatically setting up the Appium server, ensuring smooth test execution.
- **Emulator Management:** Offering functionality to programmatically launch and terminate the Android emulator for seamless automation.
- **Element-Wait Appium Plugin:** Integrating the element-wait Appium plugin for optimized synchronization and element identification.
- **Singleton Design Pattern:** Implementing a Singleton design pattern for managing the BaseDriver instance, ensuring efficient resource utilization.
- **Page Object Design Pattern:** Embracing the page object design pattern for scalable and maintainable code structure.
- **Logging:** Utilizing Appium logs and Log4j2 for comprehensive and structured logging during test execution.
- **Extent Reports:** Incorporating the latest extent reports from the Avent Stack to generate detailed and interactive test reports.
- **TestNG 7.9:** Utilizing the latest TestNG version for test management and execution.
- **Config:** Includes configuration files for managing elements locator strategies and emulator properties separately, enhancing maintainability.
- **Video Clips:** records clips for running test cases and gets them saved in project directory.

## Getting Started

To run these automated tests, ensure you have the following prerequisites installed:

- **Appium:** Install Appium by following the instructions from the [Appium Installation Guide](https://appium.io/docs/en/about-appium/getting-started/).
- **Android Studio:** Download and set up Android Studio for managing Android emulators. Refer to the [Android Studio Installation Guide](https://developer.android.com/studio/install) for detailed instructions.
- **Java Development Kit (JDK):** Install JDK 8 or later versions and ensure it's properly configured in your environment.

Then, follow these steps:

1. **Clone the Repository:** `git clone https://github.com/PierreKhouzam/ikea-appium-demo.git`
2. **Install Dependencies:** `mvn install` in the project root directory.
3. **Optional: Emulator Configuration:** Configure emulator properties in `config/emulator.properties` if necessary. The project can start an emulator programmatically or use any running emulator.
4. **Run using TestNG:** Directly run from 'tests/IkeaSuite.java'


## Test Cases

### TC1: Validate Product Details Consistency

- **Description:** Verifies the consistency of product information between listing and details pages.
- **Steps:**
  1. Navigate to the desired product from the home page.
  2. Capture product details from the listing and details pages.
  3. Compare product names and prices between the two pages.
- **Assertions:**
  - Ensures that the product name and price match between the listing and details pages.

### TC2: Validate Cart Functionality

- **Description:** Ensures the cart is empty post product removal.
- **Steps:**
  1. Add products to the cart and navigate through different sections.
  2. Remove a product from the cart and verify its absence.
  3. Check if the recommended section is displayed in the cart.
- **Assertions:**
  - Confirms that the removed product is no longer displayed in the cart.
  - Validates the presence of the recommended section in the cart.


## Feel free to add more Test Cases..
