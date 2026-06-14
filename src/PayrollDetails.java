/**
 * PayrollDetails.java - Holds all deduction and net salary information
 *
 * This is a simple DATA HOLDER class (sometimes called a DTO - Data Transfer Object)
 *
 * WHY DO WE NEED THIS?
 * When calculating payroll, we have multiple values: tax, pension, health insurance, net salary
 * Instead of returning 4 separate values, we package them into ONE object
 * This makes code cleaner and easier to understand
 *
 * NO LOGIC HERE - Just stores data and provides getters to access it
 */

public class PayrollDetails {

    // ===== PROPERTIES (ALL PRIVATE - ENCAPSULATION) =====
    private double tax;              // Tax deducted from salary (20% or 30% for high income)
    private double pension;          // Pension contribution (5% of gross salary)
    private double healthInsurance;  // Fixed health insurance amount ($500)
    private double netSalary;        // Final salary after ALL deductions (what employee actually gets)

    // ===== CONSTRUCTOR =====
    // Takes all 4 values and stores them in the object
    // Called by TaxCalculator.calculateDeductions() after computing everything
    public PayrollDetails(double tax, double pension, double healthInsurance, double netSalary) {
        this.tax = tax;
        this.pension = pension;
        this.healthInsurance = healthInsurance;
        this.netSalary = netSalary;
    }

    // ===== GETTER METHODS =====
    // Allow outside code to READ the values but NOT modify them
    // No setters because once a PayrollDetails object is created, deductions shouldn't change
    public double getTax() { return tax; }
    public double getPension() { return pension; }
    public double getHealthInsurance() { return healthInsurance; }
    public double getNetSalary() { return netSalary; }
}