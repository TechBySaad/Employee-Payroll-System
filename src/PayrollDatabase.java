
import java.io.*;
import java.util.*;

public class PayrollDatabase {
    private static final String EMPLOYEE_FILE = "employees.dat";
    private static final String PAYSLIP_FILE = "payslips.dat";

    public static void saveEmployees(ArrayList<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EMPLOYEE_FILE))) {
            oos.writeObject(employees);
            System.out.println("✅ Employees saved to database");
        } catch (IOException e) {
            System.out.println("❌ Error saving employees: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Employee> loadEmployees() {
        File file = new File(EMPLOYEE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error loading employees: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void savePaySlip(PaySlip slip) {
        try (FileWriter fw = new FileWriter(PAYSLIP_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(slip);
        } catch (IOException e) {
            System.out.println("❌ Error saving payslip: " + e.getMessage());
        }
    }
}
