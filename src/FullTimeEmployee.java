/**
 * FullTimeEmployee.java - Represents employees with fixed monthly salary
 *
 * This class extends Employee (inherits all its properties and methods)
 *
 * WHY A SEPARATE CLASS?
 * Full-time employees are different from part-time because:
 * 1. They get a FIXED monthly salary (not hourly)
 * 2. They DON'T get overtime pay (salary covers all hours)
 * 3. They typically get benefits (health insurance, pension, etc.)
 *
 * By extending Employee, we reuse all the code from parent class (name, id, job, attendance, etc.)
 * We only need to write what's DIFFERENT - which is salary calculation
 */

public class FullTimeEmployee extends Employee {  // 'extends' means FullTimeEmployee IS-A Employee

    // ===== UNIQUE PROPERTY =====
    // Full-time employees have a monthly salary (part-time employees don't have this)
    private double monthlySalary;  // Fixed amount paid each month before deductions

    // ===== CONSTRUCTOR =====
    // Parameters: name, id, job (from parent) + monthlySalary (unique to FullTime)
    public FullTimeEmployee(String name, int id, String job, double monthlySalary){

        // SUPER() - Calls the PARENT class (Employee) constructor FIRST
        // This initializes name, id, job, and creates Attendance object
        // SUPER must be the FIRST line in constructor
        super(name, id, job);  // Passing to Employee constructor

        // AFTER parent is initialized, set our own property
        this.monthlySalary = monthlySalary;  // Store the monthly salary
    }

    // ===== IMPLEMENTING THE ABSTRACT METHOD =====
    // Employee class declared calculateSalary() as ABSTRACT
    // That means EVERY subclass MUST provide their own implementation
    //
    // For FullTimeEmployee: salary = monthly salary (simple!)
    // For PartTimeEmployee: salary = hours × rate (different logic)
    @Override  // Tells compiler: "I'm overriding a method from parent class"
    public double calculateSalary(){
        return monthlySalary;  // Just return the fixed monthly amount
    }

    // NOTE: We DON'T need to override calculateSalaryWithOvertime()
    // Because the parent class version already handles:
    // - FullTime employees don't get overtime (returns base salary only)
    // - That's exactly what we want!
}

// ===== HOW INHERITANCE WORKS HERE =====
//
// FullTimeEmployee object ACTUALLY contains:
//
// ┌─────────────────────────────────┐
// │     FullTimeEmployee Object     │
// ├─────────────────────────────────┤
// │  FROM EMPLOYEE (parent):        │
// │  - name                         │
// │  - id                           │
// │  - job                          │
// │  - attendance                   │
// │  - bankAccount                  │
// │  - paymentMethod                │
// ├─────────────────────────────────┤
// │  FROM FULLTIMEEMPLOYEE (child): │
// │  - monthlySalary  ← ONLY THIS   │
// └─────────────────────────────────┘
//
// This is why inheritance is powerful - we write common code ONCE in Employee,
// and each subclass only adds what makes it unique!