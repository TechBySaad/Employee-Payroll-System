import java.util.ArrayList;

public class PayrollSystem {
    private ArrayList<Employee> employeesList;

    public PayrollSystem(){
        employeesList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeesList.add(employee);
    }

    public void removeEmployee(int id) {
       Employee employeeToRemove = null;
       for(Employee employee:employeesList){
           if (employee.getId() == id) {
               employeeToRemove = employee;
               break;
           }
       }
        if (employeeToRemove != null) {
            employeesList.remove(employeeToRemove);
        }
    }

    public void displayEmployees(){
        for (Employees emplyee : employeesList) {
            System.out.println(emplyee);
        }

    }
}
