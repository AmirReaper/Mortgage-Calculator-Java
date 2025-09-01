# Changelog

All notable changes to this project will be documented in this file.

## [1.1.0] - 2025-08-30
### Changed
- Refactored the project from a procedural approach to an **object-oriented design**.
- Introduced the following classes:
  - `MortgageCalculator` → Handles all mortgage-related calculations.
  - `MortgageReport` → Responsible for displaying results (monthly payment and payment schedule).
  - `Console` → Provides safe and validated user input handling.
- Improved code readability, reusability, and maintainability by applying OOP principles.

## [1.0.0] - Initial Release - 2025-08-20
### Added
- Basic mortgage calculator implemented in a procedural style.
- Supported input for:
  - Loan principal
  - Annual interest rate
  - Loan period (in years)
- Printed monthly payment and payment schedule directly from the `main` method.
