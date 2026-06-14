public abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id){
        this.name = name;
        this.id = id;
    }

    //getter method
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    //abstract method
    abstract double calculateSalary();
    //setter method
//    public void setName(String name){
//        this.name = name;
//    }
//    public void setId(int id){
//        this.id = id;
//    }
    @Override
    public String toString(){
        return "Employee[Name: "+name+", id: "+ id + ", salary: "+ calculateSalary()+ "]";
    }
}
