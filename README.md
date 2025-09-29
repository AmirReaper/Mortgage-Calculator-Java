
---

## 📘 README.md 

```markdown
# 🏦 Mortgage Calculator v2.3.1 – Professional Architecture

This release focuses on **code quality and maintainability** through comprehensive refactoring.

---

## ✨ Architectural Improvements
- All features from v2.3.0 (Collections)
- **Layered Package Structure**: Organized code into logical packages
- **Separation of Concerns**: Each class has a single, clear responsibility
- **Single Responsibility Principle**: Clean separation of business logic
- **Improved Maintainability**: Easier to test, modify, and extend

---

## 🏗️ Professional Package Structure

src/com/example/
├── app/ # Application Layer
│ ├── Main.java # Minimal entry point
│ ├── MortgageApp.java # Workflow orchestrator
│ ├── MortgageInputHandler.java # User input management
│ ├── MortgageComparatorService.java # Comparison logic
│ ├── MortgageAdjuster.java # ARM rate adjustments
│ └── MortgageReportService.java # Report generation
├── model/ # Domain Models
│ ├── Mortgage.java
│ ├── FixedRateMortgage.java
│ ├── AdjustableRateMortgage.java
│ └── Adjustable.java
├── service/ # Business Services
│ ├── Console.java
│ ├── ConsoleReport.java
│ ├── Exportable.java
│ ├── FileReportExporter.java
│ └── MortgageReport.java
├── util/ # Utilities
│ ├── MortgageMath.java
│ ├── MortgagePolicy.java
│ ├── GenericUtils.java
│ └── MortgageComparators.java
├── repository/ # Data Access
│ ├── Repository.java
│ └── MortgageRepository.java
└── exception/ # Custom Exceptions
├── MortgageNotFoundException.java
└── ExportException.java

---

## 📚 Architecture Benefits
- **Testability**: Each service can be unit tested independently
- **Readability**: Clear separation of responsibilities
- **Scalability**: Easy to add new features without breaking existing code
- **Reusability**: Services can be reused across different application parts
- **Maintainability**: Changes are isolated to specific layers

---

# 📷 Sample Run

```

    Principal (5000 - 600000): 100000,
    Annual Interest Rate (3 - 50 percent): 5,
    Years (1 - 30): 10
    Select Mortgage Type: 1) Fixed  2) Adjustable,
    Choice (1-2): 1,
    Equal to another mortgage with same terms? true

## Mortgage

    Type: FixedRateMortgage
    Monthly Payment: \$1,060.66

## Payment Schedule

    Month   1:     \$99,356.01
    Month   2:     \$98,709.34
    ...
    Month 120:     \$0.00

    Do you want to calculate again? (y/n):

---

# 🔬 Collections Demo Output

    📊 FINAL SUMMARY (Collections Demo)
    1. All your mortgages (List - maintains order):
    📝 FixedRateMortgage { principal=100000, rate=3.8%, years=10 }

    2. Unique mortgages (Set - removes duplicates):
    🔄 FixedRateMortgage { principal=100000, rate=3.8%, years=10 }
    Original: 3, Unique: 3

    3. Processing queue (Queue - FIFO order):
    🎯 Processing #1: FixedRateMortgage { principal=100000, rate=3.8%, years=10 }

    4. Quick lookup (Map - by hashCode):
    🔑 Key: 123456 → FixedRateMortgage

    5. Advanced sorting (Streams + Comparators):
    💰 100000 - 3.8%
    📈 3.8% - $100000
    ⚠️  5.0% - $250000

    6. Aggregate calculations:
    Total monthly payments: $3,500.72
    Most expensive overall: FixedRateMortgage { principal=250000, rate=5.0%, years=30 }
    Average principal: $166,666.67

````

---

## 🛠️ Technologies

- **Java SE (JDK 8+)**  
- **IntelliJ IDEA / Eclipse** (for development)  
- **Git & GitHub** (for version control)

---

## 🚀 How to Run
```bash
# Compile all packages
javac -d out src/com/example/app/*.java src/com/example/model/*.java src/com/example/service/*.java src/com/example/util/*.java src/com/example/repository/*.java src/com/example/exception/*.java

# Run
java -cp out com.example.app.Main

---

💡 Key Architectural Concepts

- Single Responsibility Principle: Each class has one reason to change

- Separation of Concerns: Different layers handle different aspects

- Dependency Management: Clear dependencies between layers

- Clean Architecture: Organized by business domain rather than technical concerns

# 🎯 Real-World Application

- This structure mirrors professional enterprise applications, making it excellent preparation for real-world Java development roles.

## 👨‍💻 Author

**Amir Reza Tabrizi**

* [GitHub Profile](https://github.com/AmirReaper)

⭐ If you found this project useful, please give it a star!
