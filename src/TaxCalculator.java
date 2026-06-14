/**
 * TaxCalculator.java - Handles all tax and deduction calculations
 *
 * This file is responsible for the MATH behind payroll deductions
 *
 * WHY A SEPARATE CLASS?
 * - Tax rules change often (this class would be updated, not the whole system)
 * - Keeps calculation logic in ONE place (easy to modify)
 * - Different countries have different tax rules (could swap this class)
 *
 * ALL METHODS ARE STATIC because:
 * - No need to create a TaxCalculator object (no data to store)
 * - Just utility methods that take input and return output
 */

public class TaxCalculator {

    /**
     * Calculates all deductions from gross salary and returns net salary
     *
     * CURRENT TAX RULES (Simple version):
     * - Tax: 20% of gross salary (flat tax rate)
     * - Pension: 5% of gross salary (employee contribution)
     * - Health Insurance: $500 flat rate (fixed amount every month)
     *
     * FORMULA:
     * netSalary = grossSalary - tax - pension - healthInsurance
     *
     * @param grossSalary - Salary before any deductions (from calculateSalary())
     * @return PayrollDetails object containing all deduction amounts and net salary
     *
     * EXAMPLE:
     * Input: grossSalary = $5000
     * tax = $5000 × 0.20 = $1000
     * pension = $5000 × 0.05 = $250
     * healthInsurance = $500
     * netSalary = $5000 - $1000 - $250 - $500 = $3250
     *
     * In real world, tax would be more complex (slabs, exemptions, etc.)
     * But this keeps it simple for learning!
     */
    public static PayrollDetails calculateDeductions(double grossSalary) {

        // Step 1: Calculate tax (20% flat rate)
        // In real system: Could be progressive (10% for first $10k, 20% for next $20k, etc.)
        double tax = grossSalary * 0.20;

        // Step 2: Calculate pension contribution (5% for retirement savings)
        double pension = grossSalary * 0.05;

        // Step 3: Fixed health insurance premium (same for all employees)
        // In real system: Could depend on family size, plan type, etc.
        double healthInsurance = 500.0;

        // Step 4: Calculate net salary (what employee actually takes home)
        double netSalary = grossSalary - tax - pension - healthInsurance;

        // Step 5: Package all values into a single object and return
        // This is cleaner than returning 4 separate values
        return new PayrollDetails(tax, pension, healthInsurance, netSalary);
    }
}