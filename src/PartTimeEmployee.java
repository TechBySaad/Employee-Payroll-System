/**
 * PartTimeEmployee.java - Represents employees paid by the hour
 *
 * This class extends Employee (inherits all properties and methods from Employee)
 *
 * WHY A SEPARATE CLASS?
 * Part-time employees are different from full-time because:
 * 1. They get paid based on HOURS WORKED × HOURLY RATE (not fixed salary)
 * 2. They CAN get overtime pay (1.5x rate for extra hours)
 * 3. They don't get the same benefits (health insurance, pension, etc.)
 *
 * By extending Employee, we reuse all the parent code (name, id, job, attendance, etc.)
 * We only add what's DIFFERENT about part-time workers
 */

public class PartTimeEmployee extends Employee {  // 'extends' means PartTimeEmployee IS-A Employee

    // ===== UNIQUE PROPERTIES =====
    // Full-time employees have monthlySalary, part-time workers have these:
    private int hoursWorked;     // Total hours worked this month (e.g., 100 hours)
    private double hourlyRate;   // Pay per hour (e.g., $150 per hour)

    // ===== CONSTRUCTOR =====
    // Parameters: name, id, job (from parent) + hoursWorked, hourlyRate (unique to PartTime)
    public PartTimeEmployee(String name, int id, String job, int hoursWorked, double hourlyRate) {

        // SUPER() - Calls the PARENT class (Employee) constructor FIRST
        // This initializes name, id, job, and creates Attendance object
        // SUPER must be the FIRST line in constructor
        super(name, id, job);  // Passing to Employee constructor

        // AFTER parent is initialized, set our own properties
        this.hoursWorked = hoursWorked;  // Store how many hours they worked
        this.hourlyRate = hourlyRate;    // Store their hourly wage
    }

    // ===== GETTER METHODS =====
    // We need these because:
    // 1. Employee class needs getHourlyRate() to calculate overtime pay
    // 2. Outside code might need to see hours/rate
    // 3. We keep fields private but expose read-only access
    public double getHourlyRate() { return hourlyRate; }
    public int getHoursWorked() { return hoursWorked; }

    // ===== IMPLEMENTING THE ABSTRACT METHOD =====
    // Employee class declared calculateSalary() as ABSTRACT
    // That means EVERY subclass MUST provide their own implementation
    //
    // For PartTimeEmployee: salary = hours worked × hourly rate
    // For FullTimeEmployee: salary = monthly salary (different logic)
    @Override  // Tells compiler: "I'm overriding a method from parent class"
    public double calculateSalary() {
        return hoursWorked * hourlyRate;  // Calculate pay based on actual work done
    }

    // NOTE: We DON'T override calculateSalaryWithOvertime() here
    // Because the parent class (Employee) already has logic that:
    // 1. Checks if employee IS-A PartTimeEmployee using 'instanceof'
    // 2. If yes, gets hourlyRate using getHourlyRate() (which we just provided)
    // 3. Calculates overtime pay at 1.5x rate
    // 4. Adds it to base salary
    //
    // So parent class handles overtime automatically for ALL part-time employees!
}

// ===== HOW INHERITANCE WORKS HERE =====
//
// PartTimeEmployee object ACTUALLY contains:
//
// ┌─────────────────────────────────┐
// │     PartTimeEmployee Object     │
// ├─────────────────────────────────┤
// │  FROM EMPLOYEE (parent):        │
// │  - name                         │
// │  - id                           │
// │  - job                          │
// │  - attendance                   │
// │  - bankAccount                  │
// │  - paymentMethod                │
// ├─────────────────────────────────┤
// │  FROM PARTTIMEEMPLOYEE (child): │
// │  - hoursWorked  ← ONLY THESE    │
// │  - hourlyRate   ← TWO FIELDS    │
// └─────────────────────────────────┘
//
// This is why inheritance is powerful - Employee class handles ALL common stuff,
// PartTimeEmployee only adds what makes it different!