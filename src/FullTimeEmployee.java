public class FullTimeEmployee extends Employee{
   private double monthlySalary;

    public FullTimeEmployee(String name, int id, String job, double monthlySalary){
        // this calls the constructor of super class
        super(name, id, job);
        this.monthlySalary = monthlySalary;
    }
    @Override
    public double calculateSalary(){
        return monthlySalary;
    }
}
