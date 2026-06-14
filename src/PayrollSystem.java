/**
 * PayrollSystem.java - Main class that manages all employees and payroll operations
 *
 * This is the CORE MANAGER class of our payroll system
 *
 * WHAT IT DOES:
 * - Stores all employees in an ArrayList
 * - Adds new employees to the system
 * - Removes employees by ID
 * - Displays all employees
 * - Processes payroll for a given month (calculates salary, deductions, prints payslips)
 *
 * WHY SEPARATE FROM MAIN?
 * - Main should only handle user interaction
 * - PayrollSystem handles all BUSINESS LOGIC
 * - Makes code organized and reusable
 */

import java.util.ArrayList;  // For dynamic array that can grow/shrink

public class PayrollSystem {

    // ===== PROPERTY =====
    // ArrayList stores all employees (both FullTime and PartTime)
    // We use Employee type (parent class) to store both child types - POLYMORPHISM!
    private ArrayList<Employee> employeesList;

    // ===== CONSTRUCTOR =====
    // Called when we create a new PayrollSystem object
    // Initializes empty ArrayList (no employees yet)
    public PayrollSystem(){
        employeesList = new ArrayList<>();  // Empty list ready to add employees
    }

    /**
     * Adds an employee to the system
     *
     * @param employee - Any type of Employee (FullTimeEmployee or PartTimeEmployee)
     *
     * WHY THIS WORKS FOR BOTH TYPES?
     * Polymorphism - FullTimeEmployee and PartTimeEmployee are both Employee
     * ArrayList<Employee> can hold any object that IS-A Employee
     */
    public void addEmployee(Employee employee) {
        employeesList.add(employee);  // ArrayList handles the adding
    }

    /**
     * Removes an employee by their ID
     *
     * HOW IT WORKS:
     * 1. Search through list to find employee with matching ID
     * 2. If found, store reference to that employee
     * 3. After loop, remove that employee from list
     *
     * @param id - Unique employee ID to search for
     *
     * NOTE: We can't remove while iterating (would cause error)
     * So we find first, then remove outside the loop
     */
    public void removeEmployee(int id) {
        Employee employeeToRemove = null;  // Start with null (not found yet)

        // Loop through all employees to find matching ID
        for(Employee employee : employeesList){
            if (employee.getId() == id) {  // ID matches?
                employeeToRemove = employee;  // Store reference
                break;  // Stop searching (found what we need)
            }
        }

        // If we found an employee (not null), remove them
        if (employeeToRemove != null) {
            employeesList.remove(employeeToRemove);  // ArrayList removes it
        }
        // If not found, do nothing (no error message to keep it simple)
    }

    /**
     * Displays all employees in the system
     *
     * Loops through list and prints each employee
     * Uses each employee's toString() method automatically
     */
    public void displayEmployees(){
        for (Employee employee : employeesList) {
            System.out.println(employee);  // Calls employee.toString() automatically
        }
    }

    /**
     * Processes payroll for all employees for a specific month
     *
     * WHAT HAPPENS FOR EACH EMPLOYEE:
     * 1. Get gross salary (base salary, no overtime in this version)
     * 2. Calculate deductions (tax, pension, health insurance)
     * 3. Create a PaySlip with all the information
     * 4. Print the pay slip
     *
     * @param month - Which month we're processing (e.g., "December 2024")
     */
    public void processPayroll(String month) {
        // Print header
        System.out.println("\n Processing Payroll for " + month);
        System.out.println("================================");

        // Process each employee one by one
        for (Employee employee : employeesList) {

            // Step 1: Get gross salary
            // This calls calculateSalary() polymorphically
            // FullTimeEmployee returns monthlySalary
            // PartTimeEmployee returns hoursWorked × hourlyRate
            double grossSalary = employee.calculateSalary();

            // Step 2: Calculate taxes and deductions
            // Pass gross salary to TaxCalculator, get back all deduction amounts
            PayrollDetails deductions = TaxCalculator.calculateDeductions(grossSalary);

            // Step 3: Create a PaySlip object with all payroll data
            PaySlip slip = new PaySlip(
                    employee.getName(),      // Employee's name
                    employee.getId(),        // Employee's ID
                    grossSalary,             // Salary before deductions
                    deductions,              // Object containing tax, pension, etc.
                    month                    // Which month this is for
            );

            // Step 4: Print the pay slip
            slip.print();  // Displays formatted pay slip to console
        }
    }
}