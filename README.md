
---

## 📘 README.md 

```markdown
# 🏦 Mortgage Calculator v2.2.0 – Generics

This release extends the project with **Generics** features.

## ✨ New Features
- All features from v2.1.0 (Exceptions)
- **Generic Repository<T, ID> interface**
- **MortgageRepository implementation** with Iterable<Mortgage> support
- **GenericUtils** with:
    - `findMax(List<T>)` with bounded types
    - `swap(List<T>, i, j)` method
    - `totalPaymentsWildcard(List<? extends Mortgage>)` with wildcards
- **MortgageComparators** factory for sorting

## 📚 Learning Goals
- Master type parameters and bounded types
- Implement reusable generic repositories
- Explore wildcard generics for collections
- Understand generic methods and type inference

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

---

# 🔬 Generics Demo Output

--- DEMO: Generics Features ---
All mortgages in repository:
- FixedRateMortgage { principal= 100000, rate= 5%, years= 1 }
- AdjustableRateMortgage { principal= 150000, rate= 4.5%, years= 2 }

Most expensive mortgage (using GenericUtils.findMax): 
FixedRateMortgage { principal= 100000, rate= 5%, years= 1 }
Total monthly payments (using wildcard): $1,060.66

# 🏗️ Enhanced Project Structure

src/com/example/
├── [All v2.1.0 files...]
├── Repository.java           # Generic repository interface
├── MortgageRepository.java   # Repository implementation  
├── GenericUtils.java         # Generic utility methods
└── MortgageComparators.java  # Comparator factory

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
