// TaxCalculator.java
public class TaxCalculator {
    private static final double TAX_RATE = 0.20; // 20% base tax
    private static final double HIGH_INCOME_THRESHOLD = 50000;
    private static final double HIGH_INCOME_TAX_RATE = 0.30;
    private static final double PENSION_RATE = 0.05;
    private static final double HEALTH_INSURANCE = 500.0;

    public static PayrollDetails calculateDeductions(double grossSalary) {
        double tax = grossSalary * TAX_RATE;
        if (grossSalary > HIGH_INCOME_THRESHOLD) {
            double excess = grossSalary - HIGH_INCOME_THRESHOLD;
            tax = (HIGH_INCOME_THRESHOLD * TAX_RATE) + (excess * HIGH_INCOME_TAX_RATE);
        }

        double pension = grossSalary * PENSION_RATE;
        double totalDeductions = tax + pension + HEALTH_INSURANCE;
        double netSalary = grossSalary - totalDeductions;

        return new PayrollDetails(tax, pension, HEALTH_INSURANCE, totalDeductions, netSalary);
    }
}
