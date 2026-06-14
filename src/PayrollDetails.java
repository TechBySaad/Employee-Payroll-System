// PayrollDetails.java
public class PayrollDetails {
    private double tax;
    private double pension;
    private double healthInsurance;
    private double totalDeductions;
    private double netSalary;

    public PayrollDetails(double tax, double pension, double healthInsurance,
                          double totalDeductions, double netSalary) {
        this.tax = tax;
        this.pension = pension;
        this.healthInsurance = healthInsurance;
        this.totalDeductions = totalDeductions;
        this.netSalary = netSalary;
    }

    // Getters
    public double getTax() { return tax; }
    public double getPension() { return pension; }
    public double getHealthInsurance() { return healthInsurance; }
    public double getTotalDeductions() { return totalDeductions; }
    public double getNetSalary() { return netSalary; }
}
