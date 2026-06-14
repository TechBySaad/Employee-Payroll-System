/**
 * Employee.java - Abstract base class for all employee types
 *
 * This file is the BLUEPRINT for all employees in our system.
 * We made it abstract because:
 * 1. We don't want to create generic "Employee" objects (they'd be meaningless)
 * 2. We want to FORCE subclasses (FullTimeEmployee, PartTimeEmployee) to implement calculateSalary()
 * 3. It allows us to use polymorphism - treating all employees as one type
 *
 * By implementing Serializable, we can save employee data to files
 */

import java.io.Serializable;  // Allows saving objects to files

public abstract class Employee implements Serializable {

    private static final long serialVersionUID = 1L;  // Version number for file saving

    // ===== PROPERTIES (ALL PRIVATE FOR ENCAPSULATION) =====
    private String name;           // Employee's full name
    private int id;                // Unique identifier (used for searching/removing)
    private String job;            // Job title/designation
    private Attendance attendance; // Tracks leaves, overtime, attendance (composition)
    private String bankAccount;    // For salary transfers
    private String paymentMethod;  // How they get paid (Bank Transfer, Check, Cash)

    // ===== CONSTRUCTOR =====
    // Called when creating ANY employee (FullTime OR PartTime)
    // Notice: We create an Attendance object automatically for each employee
    public Employee(String name, int id, String job) {
        this.name = name;
        this.id = id;
        this.job = job;
        this.attendance = new Attendance(id);  // COMPOSITION: Employee HAS-A Attendance
        this.paymentMethod = "Bank Transfer";   // Default payment method
    }

    // ===== GETTER METHODS =====
    // Allow outside code to READ private data but not modify it directly
    public String getName() { return name; }
    public int getId() { return id; }
    public String getJob() { return job; }
    public Attendance getAttendance() { return attendance; }
    public String getPaymentMethod() { return paymentMethod; }

    // ===== SETTER METHODS =====
    // Allow outside code to MODIFY specific private data
    public void setPaymentMethod(String method) { this.paymentMethod = method; }
    public void setBankAccount(String account) { this.bankAccount = account; }
    public String getBankAccount() { return bankAccount; }

    // ===== ABSTRACT METHOD =====
    // THIS is why Employee class is abstract!
    // Every subclass MUST implement their own calculateSalary() logic
    // FullTimeEmployee returns monthlySalary
    // PartTimeEmployee returns hoursWorked × hourlyRate
    abstract double calculateSalary();

    // ===== METHOD WITH POLYMORPHISM =====
    // Calculates salary INCLUDING overtime pay
    // Only PartTime employees get overtime, FullTime employees don't
    //
    // HOW IT WORKS:
    // 1. Get base salary by calling calculateSalary() (polymorphic call!)
    // 2. Check if this employee IS-A PartTimeEmployee using 'instanceof'
    // 3. If yes, cast 'this' to PartTimeEmployee to access getHourlyRate()
    // 4. Calculate overtime pay and add to base salary
    // 5. If FullTime, just return base salary (no overtime)
    public double calculateSalaryWithOvertime() {
        double baseSalary = calculateSalary();  // This calls the SUBCLASS version!

        if (this instanceof PartTimeEmployee) {  // Only part-time workers get overtime
            PartTimeEmployee pte = (PartTimeEmployee) this;  // Cast to access PartTime methods
            double overtimePay = attendance.getOvertimePay(pte.getHourlyRate());
            return baseSalary + overtimePay;
        }
        return baseSalary;  // Full-time employees: no overtime pay
    }

    // ===== TOSTRING METHOD =====
    // Called automatically when we print an Employee object
    // String.format() makes it easy to format numbers with 2 decimal places ($)
    @Override
    public String toString() {
        return String.format("Employee[Name: %s, ID: %d, Job: %s, Salary: $%.2f]",
                name, id, job, calculateSalary());
    }
}