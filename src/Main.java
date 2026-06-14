

public class Main {
    public static void main(String[] args) {

        PayrollSystem payrollSystem = new PayrollSystem();


        FullTimeEmployee emp1 = new FullTimeEmployee("Mohammed Saad", 101, "Founder and CEO", 155000.0);
        FullTimeEmployee emp2 = new FullTimeEmployee("Elon Musk", 102, "Co-Founder and CTO", 145000.0);
        FullTimeEmployee emp3 = new FullTimeEmployee("Andrej Karpathy", 103, "Tech & AI Lead", 135000.0);
        FullTimeEmployee emp4 = new FullTimeEmployee("Ilya Sutskever", 104, "Systems & Backend Lead", 120000.0);
        FullTimeEmployee emp5 = new FullTimeEmployee("James Gosling", 105, "Principal Software Architect", 125000.0);
        FullTimeEmployee emp6 = new FullTimeEmployee("Johny Ive", 106, "Chief Product & UI/UX Designer", 115000.0);
        FullTimeEmployee emp7 = new FullTimeEmployee("Gary Vaynerchuk", 107, "Head of Growth & Marketing", 95000.0);
        FullTimeEmployee emp8 = new FullTimeEmployee("Marc Benioff", 108, "Founding Sales Representative", 90000.0);

        PartTimeEmployee emp9  = new PartTimeEmployee("Linus Torvalds", 201, "DevOps & Linux Infrastructure", 100, 150);
        PartTimeEmployee emp10 = new PartTimeEmployee("Martin Fowler", 202, "Software Architecture Advisor", 95, 100);
        PartTimeEmployee emp11 = new PartTimeEmployee("Marques Brownlee", 203, "Tech Media & Brand Specialist", 85, 150);
        PartTimeEmployee emp12 = new PartTimeEmployee("Paul Graham", 204, "Startup Strategy Consultant", 150, 50);

        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);
        payrollSystem.addEmployee(emp3);
        payrollSystem.addEmployee(emp4);
        payrollSystem.addEmployee(emp5);
        payrollSystem.addEmployee(emp6);
        payrollSystem.addEmployee(emp7);
        payrollSystem.addEmployee(emp8);
        payrollSystem.addEmployee(emp9);
        payrollSystem.addEmployee(emp10);
        payrollSystem.addEmployee(emp11);
        payrollSystem.addEmployee(emp12);


        System.out.println("Initial Employee Details: ");
        System.out.println();
        payrollSystem.displayEmployees();
        System.out.println();
        System.out.println("Removing Employees:");
        payrollSystem.removeEmployee(202);
        System.out.println();
        System.out.println("Removing Employees Details");
        payrollSystem.displayEmployees();
    }
}
