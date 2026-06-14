import java.util.*;
public class PayrollReports {

    public static void generateMonthlyReport(ArrayList<Employee> employees, String month) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("         MONTHLY PAYROLL REPORT - " + month);
        System.out.println("=".repeat(80));
        System.out.printf("%-5s %-20s %-25s %-15s %-15s%n",
                "ID", "Name", "Job", "Gross Salary", "Net Salary");
        System.out.println("-".repeat(80));

        double totalGross = 0;
        double totalNet = 0;

        for (Employee emp : employees) {
            double gross = emp.calculateSalaryWithOvertime();
            PayrollDetails deductions = TaxCalculator.calculateDeductions(gross);
            double net = deductions.getNetSalary();

            System.out.printf("%-5d %-20s %-25s $%-14.2f $%-14.2f%n",
                    emp.getId(),
                    truncate(emp.getName(), 20),
                    truncate(emp.getJob(), 25),
                    gross, net);

            totalGross += gross;
            totalNet += net;
        }

        System.out.println("-".repeat(80));
        System.out.printf("%-52s $%-14.2f $%-14.2f%n",
                "TOTAL:", totalGross, totalNet);
        System.out.println("=".repeat(80));
    }

    public static void generateDepartmentSummary(ArrayList<Employee> employees) {
        Map<String, Double> departmentCosts = new HashMap<>();

        for (Employee emp : employees) {
            String department = extractDepartment(emp.getJob());
            double salary = emp.calculateSalaryWithOvertime();
            departmentCosts.put(department, departmentCosts.getOrDefault(department, 0.0) + salary);
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("         DEPARTMENT COST SUMMARY");
        System.out.println("=".repeat(60));
        System.out.printf("%-30s %-20s%n", "Department", "Total Salary Cost");
        System.out.println("-".repeat(60));

        for (Map.Entry<String, Double> entry : departmentCosts.entrySet()) {
            System.out.printf("%-30s $%-19.2f%n", entry.getKey(), entry.getValue());
        }
        System.out.println("=".repeat(60));
    }

    private static String extractDepartment(String jobTitle) {
        if (jobTitle.contains("CEO") || jobTitle.contains("CTO")) return "Executive";
        if (jobTitle.contains("Tech") || jobTitle.contains("AI") || jobTitle.contains("Architect")) return "Engineering";
        if (jobTitle.contains("Design")) return "Design";
        if (jobTitle.contains("Marketing") || jobTitle.contains("Growth")) return "Marketing";
        if (jobTitle.contains("Sales")) return "Sales";
        if (jobTitle.contains("Consultant") || jobTitle.contains("Advisor")) return "Consulting";
        return "Other";
    }

    private static String truncate(String str, int length) {
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }
}