
---

## 📄 CHANGELOG.md

```markdown
# Changelog

All notable changes to this project are documented here.

---

## [2.0.0] - 2025-09-05
### Added
- Introduced **abstract base class `Mortgage`** with validation, encapsulation, and common logic.
- Added **`FixedRateMortgage`** and **`AdjustableRateMortgage`** subclasses.
- Added **interfaces**:
  - `Adjustable` → enables adjustable interest rates.
  - `Exportable` → allows exporting reports.
- Added **`MortgageMath`** (final utility class) for loan formulas.
- Added **`MortgagePolicy`** (final class) with domain validation constants.
- Added **`MortgageReport`** (abstract) and `ConsoleReport` (concrete) for flexible reporting.

### Changed
- Refactored from procedural/OOP-lite design into **full OOP architecture**:
  - Polymorphism for runtime selection of mortgage type.
  - Upcasting/downcasting examples.
  - Overridden calculation methods.
  - Custom `equals`/`hashCode` for comparing mortgages.
- Improved separation of concerns between input, business logic, and reporting.

### Fixed
- Input handling improved with validation and retry loop.

---

## [1.1.0] - 2025-08-30
### Changed
- First OOP refactor:
  - `MortgageCalculator` → calculations
  - `MortgageReport` → presentation
  - `Console` → user input
- Improved readability and reusability.

---

## [1.0.0] - 2025-08-20
### Added
- Initial procedural mortgage calculator.
- Supported principal, interest rate, and period input.
- Printed monthly payment and full amortization schedule.
