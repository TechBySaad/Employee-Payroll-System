// PaySlip.java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaySlip {
    private static int slipCounter = 1000;
    private int slipId;
    private Employee employee;
    private double grossSalary;
    private PayrollDetails deductions;
    private String month;
    private LocalDate paymentDate;

    public PaySlip(Employee employee, String month, double grossSalary, PayrollDetails deductions) {
        this.slipId = ++slipCounter;
        this.employee = employee;
        this.month = month;
        this.grossSalary = grossSalary;
        this.deductions = deductions;
        this.paymentDate = LocalDate.now();
    }

    public void printPaySlip() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    PAY SLIP");
        System.out.println("=".repeat(60));
        System.out.println("Slip ID: " + slipId);
        System.out.println("Payment Date: " + paymentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Month: " + month);
        System.out.println("-".repeat(60));
        System.out.println("Employee Details:");
        System.out.println("  Name: " + employee.getName());
        System.out.println("  ID: " + employee.getId());
        System.out.println("  Job: " + employee.getJob());
        System.out.println("-".repeat(60));
        System.out.println("Earnings:");
        System.out.printf("  Gross Salary: $%.2f%n", grossSalary);
        System.out.println("-".repeat(60));
        System.out.println("Deductions:");
        System.out.printf("  Tax: $%.2f%n", deductions.getTax());
        System.out.printf("  Pension: $%.2f%n", deductions.getPension());
        System.out.printf("  Health Insurance: $%.2f%n", deductions.getHealthInsurance());
        System.out.println("-".repeat(60));
        System.out.printf("Total Deductions: $%.2f%n", deductions.getTotalDeductions());
        System.out.printf("NET PAY: $%.2f%n", deductions.getNetSalary());
        System.out.println("=".repeat(60));
    }

    @Override
    public String toString() {
        return String.format("Slip %d - %s: Gross: $%.2f, Net: $%.2f",
                slipId, month, grossSalary, deductions.getNetSalary());
    }
}