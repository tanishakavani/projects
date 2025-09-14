# 🏧 ATM Machine Project  

## 📌 Project Overview  
This project is a **Graphical ATM Machine Simulation** that replicates the basic functionality of a real ATM.  
It provides secure user authentication, account creation, and core banking operations such as **cash deposit, withdrawal, balance enquiry, and fast cash**.  

The system is designed with a **user-friendly interface** and demonstrates how real-world ATM transactions are handled digitally.  

---

## 🚀 Features  
- 🔑 **User Authentication** (Card Number & PIN login)  
- 📝 **Sign Up / Registration Form** for new customers  
  - Personal details (Name, DOB, Gender, etc.)  
  - Additional details (Account type, PAN, Aadhaar, Facilities)  
- 💰 **Banking Operations**  
  - Cash Deposit  
  - Cash Withdrawal  
  - Balance Enquiry  
  - Fast Cash  
- 🏦 **Multiple Account Types** (Savings, Current, FD, RD)  
- ⚡ **Error handling** for invalid inputs and transactions  

---

## 🛠️ Tech Stack  
- **Language:** `Java` (Swing/GUI)  
- **Database:** `MySQL / File Handling` for storing user details  
- **Concepts Used:**  
  - Object-Oriented Programming (OOP)  
  - GUI Programming  
  - Exception Handling  
  - Database Connectivity (JDBC)  

---

## 📂 Project Structure  
```
ATM-Machine/
│── src/                  # Source code files
│── screenshots/          # Screenshots of project
│── database/             # Database files for import
│── lib/                  # External libraries
│── README.md             # Documentation
```

## 🧑‍💻 Author

Developed by **[Kavani Tanisha Rajubhai]** ✨

---

## ⚙️ How to Compile & Run  

1. Generate sources list:  
    ```powershell
    Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_FullName } > sources.txt

2. Compile the project (with libraries):
    javac -cp "lib/*" -d bin (Get-Content sources.txt)

3. Run the main class:
    java -cp "bin;lib/*" SignIn.Login

---

## 🎯 Future Enhancements  
- 🌐 Add **online banking / mobile integration**  
- 🔐 Encrypt card numbers and PINs for better security  
- 💳 Generate **real-time receipts** in PDF format  
- 🎨 Improve UI with modern frameworks  
 

---

✨ *Developed as a simulation of real-world ATM systems for educational purposes.*  
