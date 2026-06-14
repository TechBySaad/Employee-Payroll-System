
public class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, String job, int hoursWorked, double hourlyRate) {
        super(name, id, job);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() { return hourlyRate; }
    public int getHoursWorked() { return hoursWorked; }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}