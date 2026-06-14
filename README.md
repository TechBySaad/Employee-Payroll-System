# Employee Payroll System

A Java-based payroll system built to demonstrate Object-Oriented Programming concepts.

## Features

- Add/Remove employees (Full-time & Part-time)
- Process monthly payroll with tax deductions (20% tax, 5% pension, $500 insurance)
- Generate pay slips for each employee
- Track attendance and overtime (1.5x rate for part-time)
- Save/Load employee data from files
- Generate monthly and department-wise reports

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
- Main.java - Entry point

## OOP Concepts Used

- Abstraction (Employee abstract class)
- Inheritance (FullTimeEmployee & PartTimeEmployee extend Employee)
- Polymorphism (ArrayList stores both types)
- Encapsulation (Private fields with getters)

## Sample Output

=== PAY SLIP ===
Month: May 2026
Employee: Mohammed Saad (ID: 101)
Gross Salary: $155000.00
Tax: $31000.00
Pension: $7750.00
Health Insurance: $500.00
NET PAY: $115750.00
================

## Author

TechBySaad (Mohammed Saad)
