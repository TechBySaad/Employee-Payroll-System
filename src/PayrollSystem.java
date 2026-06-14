
import java.time.LocalDate;
import java.util.*;

public class PayrollSystem {
    private ArrayList<Employee> employeesList;
    private ArrayList<PaySlip> paySlipHistory;

    public PayrollSystem() {
        this.employeesList = PayrollDatabase.loadEmployees();
        this.paySlipHistory = new ArrayList<>();
        System.out.println("📁 Loaded " + employeesList.size() + " employees from database");
    }

    public void addEmployee(Employee employee) {
        employeesList.add(employee);
        PayrollDatabase.saveEmployees(employeesList);
        System.out.println("✅ Employee added: " + employee.getName());
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeesList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeesList.remove(employeeToRemove);
            PayrollDatabase.saveEmployees(employeesList);
            System.out.println("✅ Employee removed: " + employeeToRemove.getName());
        } else {
            System.out.println("❌ Employee not found with ID: " + id);
        }
    }

    public void processMonthlyPayroll(String month) {
        System.out.println("\n💰 PROCESSING PAYROLL FOR " + month + " 💰");
        System.out.println("-".repeat(50));

        for (Employee employee : employeesList) {
            double grossSalary = employee.calculateSalaryWithOvertime();
            PayrollDetails deductions = TaxCalculator.calculateDeductions(grossSalary);
            PaySlip slip = new PaySlip(employee, month, grossSalary, deductions);

            slip.printPaySlip();
            paySlipHistory.add(slip);
            PayrollDatabase.savePaySlip(slip);

            // Simulate payment
            processPayment(employee, deductions.getNetSalary());
        }

        System.out.println("\n✅ Payroll processed for " + employeesList.size() + " employees");
        PayrollDatabase.saveEmployees(employeesList);
    }

    private void processPayment(Employee employee, double amount) {
        System.out.printf("💵 Payment of $%.2f sent via %s to %s%n",
                amount,
                employee.getPaymentMethod(),
                employee.getBankAccount() != null ? employee.getBankAccount() : "Manual payment");
    }

    public void markAttendance(int employeeId, LocalDate date, boolean present) {
        for (Employee emp : employeesList) {
            if (emp.getId() == employeeId) {
                emp.getAttendance().markAttendance(date, present);
                System.out.println("✅ Attendance marked for " + emp.getName() + ": " + (present ? "Present" : "Absent"));
                return;
            }
        }
        System.out.println("❌ Employee not found");
    }

    public void addOvertime(int employeeId, LocalDate date, int hours) {
        for (Employee emp : employeesList) {
            if (emp.getId() == employeeId) {
                emp.getAttendance().addOvertime(date, hours);
                System.out.println("✅ Added " + hours + " overtime hours for " + emp.getName());
                return;
            }
        }
        System.out.println("❌ Employee not found");
    }

    public void displayEmployees() {
        System.out.println("\n📋 EMPLOYEE LIST 📋");
        System.out.println("-".repeat(60));
        for (Employee employee : employeesList) {
            System.out.println(employee);
        }
        System.out.println("-".repeat(60));
        System.out.println("Total Employees: " + employeesList.size());
    }

    public void displayPaySlipHistory() {
        if (paySlipHistory.isEmpty()) {
            System.out.println("📭 No pay slips generated yet");
            return;
        }

        System.out.println("\n📜 PAY SLIP HISTORY 📜");
        System.out.println("-".repeat(40));
        for (PaySlip slip : paySlipHistory) {
            System.out.println(slip);
        }
    }

    public void generateReports(String month) {
        PayrollReport.generateMonthlyReport(employeesList, month);
        PayrollReport.generateDepartmentSummary(employeesList);
    }

    public void showLeaveBalances() {
        System.out.println("\n🌴 LEAVE BALANCES 🌴");
        System.out.println("-".repeat(60));
        System.out.printf("%-20s %-15s %-15s%n", "Employee", "Sick Leaves", "Casual Leaves");
        System.out.println("-".repeat(60));

        for (Employee emp : employeesList) {
            System.out.printf("%-20s %-15d %-15d%n",
                    truncate(emp.getName(), 20),
                    emp.getAttendance().getSickLeaves(),
                    emp.getAttendance().getCasualLeaves());
        }
    }

    private String truncate(String str, int length) {
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }
}
