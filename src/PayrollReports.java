/**
 * PayrollReports.java - Generates different reports from payroll data
 *
 * This file is responsible for DISPLAYING payroll information in readable formats
 *
 * WHY SEPARATE CLASS FOR REPORTS?
 * - Keeps reporting logic separate from main PayrollSystem
 * - Makes code organized (Single Responsibility Principle)
 * - Easy to add new report types without touching other classes
 *
 * ALL METHODS ARE STATIC because:
 * - Reports don't store data - they just process and display
 * - We can call them directly: PayrollReports.generateMonthlyReport(list, "Jan")
 */

import java.util.*;  // For ArrayList, HashMap, Map

public class PayrollReports {

    /**
     * Generates a formatted monthly payroll report showing each employee's gross and net salary
     *
     * WHAT THIS DOES:
     * 1. Prints a header with the month
     * 2. Loops through all employees
     * 3. For each employee: calculates gross salary (with overtime), deductions, and net salary
     * 4. Prints a table row for each employee
     * 5. Prints total gross and net at the bottom
     *
     * @param employees - List of all employees
     * @param month - Month to show in report (e.g., "December 2024")
     */
    public static void generateMonthlyReport(ArrayList<Employee> employees, String month) {

        // Print report header with decorative lines
        System.out.println("\n" + "=".repeat(80));
        System.out.println("         MONTHLY PAYROLL REPORT - " + month);
        System.out.println("=".repeat(80));

        // printf() = formatted printing
        // %-5s = String, left-aligned, 5 spaces wide
        // %-20s = String, left-aligned, 20 spaces wide
        // %-15s = String, left-aligned, 15 spaces wide
        // %n = new line (platform independent)
        System.out.printf("%-5s %-20s %-25s %-15s %-15s%n",
                "ID", "Name", "Job", "Gross Salary", "Net Salary");
        System.out.println("-".repeat(80));

        // Track totals for summary at bottom
        double totalGross = 0;
        double totalNet = 0;

        // Loop through every employee
        for (Employee emp : employees) {

            // Get gross salary (base salary + overtime if applicable)
            double gross = emp.calculateSalaryWithOvertime();

            // Calculate taxes and deductions
            PayrollDetails deductions = TaxCalculator.calculateDeductions(gross);

            // Get net salary after deductions
            double net = deductions.getNetSalary();

            // Print employee row
            // $%-14.2f = currency format, left-aligned, 14 spaces, 2 decimal places
            System.out.printf("%-5d %-20s %-25s $%-14.2f $%-14.2f%n",
                    emp.getId(),
                    truncate(emp.getName(), 20),    // Cut off if name too long
                    truncate(emp.getJob(), 25),     // Cut off if job title too long
                    gross, net);

            // Add to totals
            totalGross += gross;
            totalNet += net;
        }

        // Print footer with totals
        System.out.println("-".repeat(80));
        System.out.printf("%-52s $%-14.2f $%-14.2f%n",
                "TOTAL:", totalGross, totalNet);
        System.out.println("=".repeat(80));
    }

    /**
     * Groups employees by department and shows total salary cost per department
     *
     * HOW IT WORKS:
     * 1. Creates a HashMap to store department name -> total salary
     * 2. Loops through all employees
     * 3. For each employee, figures out which department they belong to (based on job title)
     * 4. Adds their salary to that department's total
     * 5. Prints the summary table
     *
     * @param employees - List of all employees
     */
    public static void generateDepartmentSummary(ArrayList<Employee> employees) {

        // HashMap: Key = department name (String), Value = total salary for that department (Double)
        Map<String, Double> departmentCosts = new HashMap<>();

        // Calculate totals per department
        for (Employee emp : employees) {

            // Figure out which department this employee belongs to
            String department = extractDepartment(emp.getJob());

            // Get their salary (with overtime)
            double salary = emp.calculateSalaryWithOvertime();

            // Add to department total
            // getOrDefault(department, 0.0) = get existing total, or 0.0 if first time
            departmentCosts.put(department, departmentCosts.getOrDefault(department, 0.0) + salary);
        }

        // Print department summary header
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         DEPARTMENT COST SUMMARY");
        System.out.println("=".repeat(60));
        System.out.printf("%-30s %-20s%n", "Department", "Total Salary Cost");
        System.out.println("-".repeat(60));

        // Loop through each department and print its total
        for (Map.Entry<String, Double> entry : departmentCosts.entrySet()) {
            System.out.printf("%-30s $%-19.2f%n", entry.getKey(), entry.getValue());
        }
        System.out.println("=".repeat(60));
    }

    /**
     * Determines which department an employee belongs to based on their job title
     *
     * RULE-BASED LOGIC:
     * - Checks job title for keywords
     * - First match wins
     * - Returns "Other" if no keywords match
     *
     * @param jobTitle - Employee's job title
     * @return Department name (Executive, Engineering, Design, Marketing, Sales, Consulting, Other)
     */
    private static String extractDepartment(String jobTitle) {
        // Check for executive roles
        if (jobTitle.contains("CEO") || jobTitle.contains("CTO")) return "Executive";

        // Check for technical roles
        if (jobTitle.contains("Tech") || jobTitle.contains("AI") || jobTitle.contains("Architect")) return "Engineering";

        // Check for design roles
        if (jobTitle.contains("Design")) return "Design";

        // Check for marketing roles
        if (jobTitle.contains("Marketing") || jobTitle.contains("Growth")) return "Marketing";

        // Check for sales roles
        if (jobTitle.contains("Sales")) return "Sales";

        // Check for consulting roles
        if (jobTitle.contains("Consultant") || jobTitle.contains("Advisor")) return "Consulting";

        // Default if no match
        return "Other";
    }

    /**
     * Helper method to cut off long text and add "..." at the end
     *
     * WHY NEED THIS?
     * - Names or job titles might be too long for table columns
     * - Prevents messy formatting
     *
     * Example: "Senior Principal Software Architect" (30 chars)
     * truncate(text, 25) → "Senior Principal Software ..."
     *
     * @param str - Text to truncate
     * @param length - Maximum allowed length
     * @return Truncated string with "..." if needed
     */
    private static String truncate(String str, int length) {
        if (str.length() <= length) return str;  // Already short enough
        return str.substring(0, length - 3) + "...";  // Cut and add ...
    }
}