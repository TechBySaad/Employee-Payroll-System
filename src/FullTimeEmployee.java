public class FullTimeEmployee extends Employee{
   private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary){
        // this calls the constructor of super class
        super(name, id);
        this.monthlySalary = monthlySalary;
    }
    @Override
    public double calculateSalary(){
        return monthlySalary;
    }
}
