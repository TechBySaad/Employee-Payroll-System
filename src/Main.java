// Main.java - Complete Application
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        Scanner scanner = new Scanner(System.in);

        // Add demo employees if database is empty
        if (payrollSystem.employeesList.isEmpty()) {
            System.out.println("📦 No existing data found. Adding demo employees...");
            addDemoEmployees(payrollSystem);
        }

        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewEmployee(payrollSystem, scanner);
                    break;
                case 2:
                    System.out.print("Enter Employee ID to remove: ");
                    int id = scanner.nextInt();
                    payrollSystem.removeEmployee(id);
                    break;
                case 3:
                    payrollSystem.displayEmployees();
                    break;
                case 4:
                    System.out.print("Enter Month (e.g., December-2024): ");
                    String month = scanner.nextLine();
                    payrollSystem.processMonthlyPayroll(month);
                    break;
                case 5:
                    markAttendance(payrollSystem, scanner);
                    break;
                case 6:
                    addOvertime(payrollSystem, scanner);
                    break;
                case 7:
                    System.out.print("Enter Month for report: ");
                    String reportMonth = scanner.nextLine();
                    payrollSystem.generateReports(reportMonth);
                    break;
                case 8:
                    payrollSystem.showLeaveBalances();
                    break;
                case 9:
                    payrollSystem.displayPaySlipHistory();
                    break;
                case 0:
                    running = false;
                    System.out.println("👋 Thank you for using Payroll System!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        EMPLOYEE PAYROLL SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Display All Employees");
        System.out.println("4. Process Monthly Payroll");
        System.out.println("5. Mark Attendance");
        System.out.println("6. Add Overtime");
        System.out.println("7. Generate Reports");
        System.out.println("8. View Leave Balances");
        System.out.println("9. View Pay Slip History");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addDemoEmployees(PayrollSystem ps) {
        FullTimeEmployee emp1 = new FullTimeEmployee("Mohammed Saad", 101, "Founder and CEO", 155000.0);
        FullTimeEmployee emp2 = new FullTimeEmployee("Elon Musk", 102, "Co-Founder and CTO", 145000.0);
        FullTimeEmployee emp3 = new FullTimeEmployee("Andrej Karpathy", 103, "Tech & AI Lead", 135000.0);
        PartTimeEmployee emp4 = new PartTimeEmployee("Linus Torvalds", 201, "DevOps Consultant", 100, 150);
        PartTimeEmployee emp5 = new PartTimeEmployee("Martin Fowler", 202, "Software Architecture Advisor", 95, 100);

        ps.addEmployee(emp1);
        ps.addEmployee(emp2);
        ps.addEmployee(emp3);
        ps.addEmployee(emp4);
        ps.addEmployee(emp5);

        // Set bank accounts
        emp1.setBankAccount("SAAD-001-XXXX");
        emp2.setBankAccount("ELON-002-XXXX");
        emp3.setBankAccount("ANDREJ-003-XXXX");
    }

    private static void addNewEmployee(PayrollSystem ps, Scanner scanner) {
        System.out.println("\n📝 Add New Employee");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Job Title: ");
        String job = scanner.nextLine();
        System.out.print("Type (1-FullTime, 2-PartTime): ");
        int type = scanner.nextInt();

        if (type == 1) {
            System.out.print("Monthly Salary: ");
            double salary = scanner.nextDouble();
            FullTimeEmployee emp = new FullTimeEmployee(name, id, job, salary);
            scanner.nextLine();
            System.out.print("Bank Account: ");
            String bank = scanner.nextLine();
            emp.setBankAccount(bank);
            ps.addEmployee(emp);
        } else {
            System.out.print("Hours Worked: ");
            int hours = scanner.nextInt();
            System.out.print("Hourly Rate: ");
            double rate = scanner.nextDouble();
            PartTimeEmployee emp = new PartTimeEmployee(name, id, job, hours, rate);
            scanner.nextLine();
            System.out.print("Bank Account: ");
            String bank = scanner.nextLine();
            emp.setBankAccount(bank);
            ps.addEmployee(emp);
        }
    }

    private static void markAttendance(PayrollSystem ps, Scanner scanner) {
        System.out.print("Employee ID: ");
        int id = scanner.nextInt();
        System.out.print("Date (YYYY-MM-DD): ");
        String dateStr = scanner.next();
        System.out.print("Present? (true/false): ");
        boolean present = scanner.nextBoolean();

        ps.markAttendance(id, LocalDate.parse(dateStr), present);
    }

    private static void addOvertime(PayrollSystem ps, Scanner scanner) {
        System.out.print("Employee ID: ");
        int id = scanner.nextInt();
        System.out.print("Date (YYYY-MM-DD): ");
        String dateStr = scanner.next();
        System.out.print("Overtime Hours: ");
        int hours = scanner.nextInt();

        ps.addOvertime(id, LocalDate.parse(dateStr), hours);
    }
}