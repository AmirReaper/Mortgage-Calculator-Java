
---

## 📄 CHANGELOG.md

# Changelog

All notable changes to this project are documented here.

---

## [2.3.1] - 2025-09-27
### Added
- **Layered Package Structure**: Professional organization into logical packages
- **Application Layer**: `app/` package with workflow orchestration
- **Domain Layer**: `model/` package with business entities
- **Service Layer**: `service/` package with business operations
- **Utility Layer**: `util/` package with helper classes
- **Data Layer**: `repository/` package with data access patterns
- **Exception Layer**: `exception/` package with custom exceptions

### Refactored
- **Separation of Concerns**: Split monolithic Main into focused services
- **Single Responsibility**: Each class has one clear purpose
- **MortgageApp**: Main workflow orchestrator
- **MortgageInputHandler**: User input and mortgage creation
- **MortgageComparatorService**: Comparison and collections logic
- **MortgageAdjuster**: Adjustable rate modifications
- **MortgageReportService**: Report generation management

### Architecture
- **Clean Architecture**: Organized by business domain
- **Maintainability**: Easy to modify and extend individual components
- **Testability**: Independent unit testing of each layer
- **Scalability**: Foundation for enterprise application growth

---

## [2.3.0] - 2025-09-27
### Added
- **Collections Framework Integration**: Practical implementation with user data
- **List Operations**: `ArrayList` usage in MortgageRepository with enhanced for-loops
- **Set Operations**: `HashSet` for duplicate removal based on equals/hashCode
- **Queue Processing**: `LinkedList` implementation for FIFO mortgage processing simulation
- **Map Lookup**: `HashMap` for key-value storage using mortgage hashCode
- **Stream API**: Functional programming with streams for sorting, filtering, and aggregation
- **Advanced Sorting**: Multiple comparator strategies applied to user's actual data

### Enhanced
- **User Experience**: Collections demonstrations using user's real mortgage calculations
- **Practical Learning**: Real-world examples instead of artificial demos
- **Statistical Analysis**: Aggregate calculations on user's mortgage portfolio
- **Performance**: Optimized comparators and utilities for collection operations

### Demo Features
- **Final Summary**: Comprehensive collections demonstration when user exits
- **Data Processing**: Stream operations on actual user calculation history
- **Portfolio Analysis**: Statistical insights into user's mortgage choices

---

## [2.2.0] - 2025-09-27
### Added
- **Generics Implementation**: Full generics support throughout the application
- **Generic Repository Pattern**: `Repository<T, ID>` interface with type parameters
- **MortgageRepository**: Concrete implementation with `Iterable<Mortgage>` support
- **GenericUtils Class**: Utility methods demonstrating advanced generics:
    - `findMax(List<T>)`: Bounded type parameters with `Comparable<T>`
    - `swap(List<T>, i, j)`: Generic method for element swapping
    - `totalPaymentsWildcard(List<? extends Mortgage>)`: Wildcard usage demonstration
- **MortgageComparators**: Factory class for flexible sorting strategies

### Enhanced
- **Type Safety**: Compile-time type checking throughout the codebase
- **Code Reusability**: Generic components that can work with any entity type
- **Flexible Sorting**: Multiple comparison strategies for mortgages

### Demo Features
- **Generics Demo**: New `demoGenericsFeatures()` method in Main
- **Repository Usage**: Type-safe storage and retrieval operations
- **Utility Methods**: Practical examples of generic methods in action

---

## [2.1.0] - 2025-09-27
### Added
- **Exception Handling**: Comprehensive exception management system
- **Custom Exceptions**:
    - `MortgageNotFoundException`: Unchecked exception for repository lookups
    - `ExportException`: Checked exception for file operation failures
- **Robust Input Validation**: Enhanced Console class with proper error handling
- **File Export Safety**: `try-with-resources` implementation in FileReportExporter

### Enhanced
- **Error Recovery**: Graceful handling of file export failures
- **User Experience**: Friendly error messages for invalid inputs
- **Resource Management**: Automatic resource cleanup with try-with-resources
- **Validation**: Comprehensive input validation with meaningful exceptions

### Security
- **File Safety**: Proper exception handling for all file operations
- **Input Sanitization**: Protection against invalid user input

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
