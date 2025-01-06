# Payroll System

## Overview

The **Payroll System** is a Java application that calculates final salaries for employees of different types: **Permanent**, **Casual**, and **Contractual**. It uses an interactive console-based interface for employee data entry, validation, and salary calculation based on predefined deduction rates.

---

## Features

1. **Employee Types**:
   - Permanent Employee
   - Casual Employee
   - Contractual Employee

2. **Validation and Input Handling**:
   - Ensures correct data types and ranges for employee details.
   - Includes validation for age, salary, and department.

3. **Payroll Calculation**:
   - Applies specific deduction rules based on employee type.

4. **Custom Input Methods**:
   - `inputInteger`, `inputString`, `inputRegex`, and others for robust input validation.

5. **Modular Code Design**:
   - Uses interfaces, abstract classes, and inheritance for flexibility and extensibility.

---

## Code Structure

### 1. **Interface: Payroll**
Defines methods for salary calculation:
- `calculatePermanent(double salary)`
- `calculateCasual(double salary)`
- `calculateContractual(double salary)`

### 2. **Abstract Class: Employee2**
Contains shared attributes and utility methods for input validation:
- Attributes: `employeeId`, `firstName`, `lastName`, `age`, `dateOfBirth`, `department`, `yearsOfExperience`.
- Utility Methods: `inputInteger`, `inputString`, `inputRegex`, `inputSalary`.

### 3. **Concrete Classes**:
#### a. **PermanentEmployee**
Implements salary calculation with deductions for:
- **Pension**: 2%
- **Tax**: 5%
- **Health Insurance**: 2.6%

#### b. **CasualEmployee**
Calculates salary with deductions for:
- **Pension**: 2%
- **Health Insurance**: 2.6%

#### c. **ContractualEmployee**
Calculates salary with deductions for:
- **Tax**: 2.6%
- **Health Insurance**: 2.6%

---

## Usage

### Step 1: Run the Program
Compile and run the `PayrollSystem` class.

```bash
javac PayrollSystem.java
java PayrollSystem
```

### Step 2: Enter Employee Details
The program will prompt for:
- Employee Type: Choose **1 (Permanent)**, **2 (Casual)**, or **3 (Contractual)**.
- Basic Details: ID, Name, Age, Date of Birth, Department, Years of Experience.
- Salary: Input within the valid range for the selected type.

### Step 3: View Final Salary
The program calculates and displays the final salary after applying deductions.

---

## Example Interaction

```
Enter Employee Type (1: Permanent, 2: Casual, 3: Contractual): 1
Enter Employee Details:
Employee ID: 101
First Name: John
Last Name: Doe
Age: 35
Date of Birth (yyyy-mm-dd): 1988-05-12
Department: Strategy
Years of Experience: 10
Enter Salary (1,000,000 to 1,500,000): 1200000

Final Salary: 1113600.0
```

---

## Deduction Details

| Employee Type       | Deduction Details                        | Total Deduction |
|---------------------|------------------------------------------|-----------------|
| Permanent Employee  | Pension (2%), Tax (5%), Health (2.6%)   | 9.6%            |
| Casual Employee     | Pension (2%), Health (2.6%)             | 4.6%            |
| Contractual Employee| Tax (2.6%), Health (2.6%)               | 5.2%            |

---

## Validation Rules

1. **Age**: Must be between 20 and 65.
2. **Salary**:
   - Permanent: 1,000,000 - 1,500,000.
   - Casual: 700,000 - 850,000.
   - Contractual: 200,000 - 400,000.
3. **Department**: Must be one of:
   - **Accounting**, **Planning**, **Strategy**, **Human Resources**.
4. **Date of Birth**: Must follow the format `yyyy-mm-dd`.

---

## Future Enhancements

1. **Database Integration**:
   - Store employee details in a database for persistent tracking.

2. **GUI**:
   - Develop a graphical user interface for easier interaction.

3. **Additional Employee Types**:
   - Support for part-time or freelance employees with unique rules.

