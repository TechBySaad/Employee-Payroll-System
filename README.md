# Employee Payroll System

A Java-based payroll system with interactive menu, built to demonstrate Object-Oriented Programming concepts.

## Features

- Interactive menu (Add/Remove/Display/Process Payroll)
- Full-time (fixed salary) & Part-time employees (hourly rate)
- Monthly payroll with tax deductions (20% tax, 5% pension, $500 insurance)
- Printable pay slips for each employee
- Attendance and overtime tracking (1.5x rate for part-time)
- Save/Load employee data from files automatically
- Monthly and department-wise reports


## Project Files

- Employee.java - Abstract base class
- FullTimeEmployee.java - Fixed monthly salary
- PartTimeEmployee.java - Hourly rate employee
- Attendance.java - Track leaves & overtime
- TaxCalculator.java - Calculate deductions
- PayrollDetails.java - Holds deduction data
- PaySlip.java - Print pay slip
- PayrollSystem.java - Core manager class
- PayrollReports.java - Generate reports
- PayrollDatabase.java - Save/load to file
- Main.java - Entry point with interactive menu

## OOP Concepts Used

- Abstraction (Employee abstract class)
- Inheritance (FullTimeEmployee & PartTimeEmployee extend Employee)
- Polymorphism (ArrayList stores both types)
- Encapsulation (Private fields with getters)

## Sample Output

===== PAYROLL SYSTEM =====
1. Add Full-Time Employee
2. Add Part-Time Employee
3. Remove Employee
4. Display All Employees
5. Process Payroll
6. Exit
Choose: 4

No employees found in the system.

=== PAY SLIP ===
Month: June 2026
Employee: Mohammed Saad (ID: 101)
Gross Salary: $155000.00
Tax: $31000.00
Pension: $7750.00
Health Insurance: $500.00
NET PAY: $115750.00
================

## Author

TechBySaad (Mohammed Saad)
