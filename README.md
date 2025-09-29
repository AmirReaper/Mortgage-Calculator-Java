
---

## 📘 README.md 

```markdown
# 🏦 Mortgage Calculator v2.3.0 – Collections Framework

    This release integrates the **Java Collections Framework** with practical, user-focused demonstrations.

## ✨ Enhanced Features
    - All features from v2.2.0 (Generics)
    - **Collections Framework Integration**:
        - **List**: Ordered storage with `ArrayList` in MortgageRepository
        - **Set**: Duplicate removal with `HashSet` based on equals/hashCode
        - **Queue**: FIFO processing simulation with `LinkedList`
        - **Map**: Key-value lookup with `HashMap` using hashCode
        - **Streams**: Functional processing with filtering and sorting
    - **Advanced Sorting**: Multiple comparator strategies with real user data
    - **Aggregate Calculations**: Statistical analysis of user's mortgage portfolio

## 📚 Learning Goals
    - Master Java Collections Framework in practical scenarios
    - Understand different collection types and their use cases
    - Practice Stream API for functional operations
    - Implement sorting with custom Comparators on real data
    - Learn iteration patterns with enhanced for-loops and streams

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
