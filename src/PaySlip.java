/**
 * PaySlip.java - Represents a single payment document for an employee
 *
 * This is like a REAL pay slip you would get at work - it shows:
 * - How much you earned before deductions (Gross Salary)
 * - What was taken out (Tax, Pension, Health Insurance)
 * - What you actually take home (Net Pay)
 *
 * WHY DO WE NEED THIS?
 * - Employees need documentation of their pay
 * - Legal requirement in most countries
 * - Shows transparency in deductions
 * - Record keeping for both employee and employer
 */

public class PaySlip {

    // ===== PROPERTIES - Information needed for ONE pay slip =====
    private String employeeName;      // Who is getting paid
    private int employeeId;           // Unique identifier (in case of same names)
    private double grossSalary;       // Pay BEFORE deductions (base salary)
    private PayrollDetails deductions; // All deduction amounts (tax, pension, insurance)
    private String month;             // Which month this pay is for (e.g., "December 2024")

    /**
     * CONSTRUCTOR - Creates a pay slip with all the payroll information
     *
     * @param employeeName - Name of the employee
     * @param employeeId - Employee's unique ID
     * @param grossSalary - Salary before any deductions
     * @param deductions - Object containing tax, pension, health insurance, and net salary
     * @param month - Month being paid for
     *
     * NOTE: We don't pass netSalary separately because it's INSIDE the deductions object
     */
    public PaySlip(String employeeName, int employeeId, double grossSalary,
                   PayrollDetails deductions, String month) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.grossSalary = grossSalary;
        this.deductions = deductions;  // This object has tax, pension, insurance, AND netSalary
        this.month = month;
    }

    /**
     * Prints a nicely formatted pay slip to the console
     *
     * This is what the employee would see or print out
     *
     * WHY PRINT INSTEAD OF TOSTRING?
     * - toString() is usually for debugging
     * - print() makes it clear we're formatting for human reading
     * - We might add file saving later (save to PDF, email, etc.)
     *
     * The format is simple but shows all important information:
     * 1. Document header (PAY SLIP)
     * 2. Month (so employee knows which period)
     * 3. Employee identification
     * 4. Earnings section (Gross Salary)
     * 5. Deductions section (everything taken out)
     * 6. Final amount (NET PAY)
     */
    public void print() {
        // Header - decorative lines to make it look official
        System.out.println("\n=== PAY SLIP ===");

        // Basic identification
        System.out.println("Month: " + month);
        System.out.println("Employee: " + employeeName + " (ID: " + employeeId + ")");

        // Blank line for readability (just visual separation)

        // EARNINGS section
        System.out.println("Gross Salary: $" + grossSalary);

        // DEDUCTIONS section - each deduction shown separately
        System.out.println("Tax: $" + deductions.getTax());               // Government takes this
        System.out.println("Pension: $" + deductions.getPension());       // Retirement savings
        System.out.println("Health Insurance: $" + deductions.getHealthInsurance()); // Medical

        // FINAL PAY section - what employee actually receives
        System.out.println("NET PAY: $" + deductions.getNetSalary());

        // Footer
        System.out.println("================");
    }
}