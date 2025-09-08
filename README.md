
---

## 📘 README.md 

```markdown
# 🏦 Mortgage Calculator v2.0.0 (Java, OOP)

This project is an **Object-Oriented Mortgage Calculator** written in Java.  
It calculates monthly loan payments and generates an amortization schedule.  
The project demonstrates **advanced OOP concepts** in a practical, real-world domain.

---

## ✨ Features
- Supports **Fixed-Rate** and **Adjustable-Rate** mortgages  
- **Polymorphism**: work with mortgages via abstract base class  
- **Inheritance & Abstraction**: common behavior encapsulated in `Mortgage` base class  
- **Interfaces (Multiple Inheritance)**: 
  - `Adjustable` → allows interest rate adjustments  
  - `Exportable` → export reports as text  
- **Encapsulation & Access Modifiers** (`private`, `protected`, `public`)  
- **Overriding**: calculation methods differ in subclasses  
- **Upcasting / Downcasting**: select mortgage type at runtime, safely cast when needed  
- **Equality**: custom `equals`/`hashCode` for meaningful comparisons  
- **Final classes** for utility (`MortgageMath`) and domain constants (`MortgagePolicy`)  
- **Report Layer**: abstract `MortgageReport` decouples business logic from presentation (`ConsoleReport`)  

---

## 📷 Sample Run

```

Principal (5000 - 600000): 100000,
Annual Interest Rate (3 - 50 percent): 5,
Years (1 - 30): 10
Select Mortgage Type: 1) Fixed  2) Adjustable,
Choice (1-2): 1,
Equal to another mortgage with same terms? true

# Mortgage

Type: FixedRateMortgage
Monthly Payment: \$1,060.66

# Payment Schedule

Month   1:     \$99,356.01
Month   2:     \$98,709.34
...
Month 120:     \$0.00

Do you want to calculate again? (y/n):

````

---

## 🛠️ Technologies
- **Java SE (JDK 8+)**  
- **IntelliJ IDEA / Eclipse** (for development)  
- **Git & GitHub** (for version control)

---

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/AmirReaper/mortgage-calculator-java.git
````

2. Open in your IDE.
3. Run `Main.java`.

---

## 👨‍💻 Author

**Amir Reza Tabrizi**

* [GitHub Profile](https://github.com/AmirReaper)

⭐ If you found this project useful, please give it a star!
