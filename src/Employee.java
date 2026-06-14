public abstract class Employee {
    private String name;
    private int id;
    private String job;

    public Employee(String name, int id, String job){
        this.name = name;
        this.id = id;
        this.job = job;
    }

    //getter method
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public String getJob(){
        return job;
    }

    //abstract method
    abstract double calculateSalary();

    @Override
    public String toString(){
        return "Employee[Name: "+name+", ID: "+ id +", Job: "+ job+ ", Salary: "+ calculateSalary()+ "]";
    }
}
