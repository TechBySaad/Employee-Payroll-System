/**
 * Main.java - Entry point of our payroll system
 *
 * This file is the "run button" of our application. It creates employees,
 * adds them to the system, processes payroll, and shows us everything working.
 */

public class Main {
    public static void main(String[] args) {

        // Creating the payroll system - this object will manage all employees
        PayrollSystem payrollSystem = new PayrollSystem();

        // ===== CREATING FULL-TIME EMPLOYEES =====
        // Full-time employees get a fixed monthly salary regardless of hours worked
        // Constructor parameters: (name, id, job title, monthly salary)
        FullTimeEmployee emp1 = new FullTimeEmployee("Mohammed Saad", 101, "Founder and CEO", 155000.0);
        FullTimeEmployee emp2 = new FullTimeEmployee("Elon Musk", 102, "Co-Founder and CTO", 145000.0);
        FullTimeEmployee emp3 = new FullTimeEmployee("Andrej Karpathy", 103, "Tech & AI Lead", 135000.0);
        FullTimeEmployee emp4 = new FullTimeEmployee("Ilya Sutskever", 104, "Systems & Backend Lead", 120000.0);
        FullTimeEmployee emp5 = new FullTimeEmployee("James Gosling", 105, "Principal Software Architect", 125000.0);
        FullTimeEmployee emp6 = new FullTimeEmployee("Johny Ive", 106, "Chief Product & UI/UX Designer", 115000.0);
        FullTimeEmployee emp7 = new FullTimeEmployee("Gary Vaynerchuk", 107, "Head of Growth & Marketing", 95000.0);
        FullTimeEmployee emp8 = new FullTimeEmployee("Marc Benioff", 108, "Founding Sales Representative", 90000.0);

        // ===== CREATING PART-TIME EMPLOYEES =====
        // Part-time employees get paid based on hours worked × hourly rate
        // Constructor parameters: (name, id, job title, hours worked, hourly rate)
        PartTimeEmployee emp9  = new PartTimeEmployee("Linus Torvalds", 201, "DevOps & Linux Infrastructure", 100, 150);
        PartTimeEmployee emp10 = new PartTimeEmployee("Martin Fowler", 202, "Software Architecture Advisor", 95, 100);
        PartTimeEmployee emp11 = new PartTimeEmployee("Marques Brownlee", 203, "Tech Media & Brand Specialist", 85, 150);
        PartTimeEmployee emp12 = new PartTimeEmployee("Paul Graham", 204, "Startup Strategy Consultant", 150, 50);

        // ===== ADDING ALL EMPLOYEES TO PAYROLL SYSTEM =====
        // Notice: We're passing both FullTimeEmployee AND PartTimeEmployee objects
        // This works because of POLYMORPHISM - both classes extend Employee class
        // The payroll system treats them all as "Employee" type
        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);
        payrollSystem.addEmployee(emp3);
        payrollSystem.addEmployee(emp4);
        payrollSystem.addEmployee(emp5);
        payrollSystem.addEmployee(emp6);
        payrollSystem.addEmployee(emp7);
        payrollSystem.addEmployee(emp8);
        payrollSystem.addEmployee(emp9);
        payrollSystem.addEmployee(emp10);
        payrollSystem.addEmployee(emp11);
        payrollSystem.addEmployee(emp12);

        // Display all employees before any changes
        System.out.println("Initial Employee Details: ");
        payrollSystem.displayEmployees();  // This loops through and prints each employee

        // Process payroll for May 2026
        // This will: calculate salary, apply tax deductions, generate pay slips
        System.out.println("\nProcessing Payroll for May 2026:");
        payrollSystem.processPayroll("May 2026");

        // Remove employee with ID 202 (Martin Fowler - our Architecture Advisor)
        // Maybe he resigned or got a better offer :(
        System.out.println("\nRemoving Employee ID 202:");
        payrollSystem.removeEmployee(202);

        // Display all employees again to confirm removal worked
        System.out.println("\nUpdated Employee Details:");
        payrollSystem.displayEmployees();
    }
}