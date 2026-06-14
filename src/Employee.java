public abstract class Employee {

    public abstract class Employee {
        private String name;
        private int id;
        private String job;
        private Attendance attendance;
        private String bankAccount;
        private String paymentMethod;

        public Employee(String name, int id, String job) {
            this.name = name;
            this.id = id;
            this.job = job;
            this.attendance = new Attendance(id);
            this.paymentMethod = "Bank Transfer";
        }

        // Existing getters...
        public String getName() { return name; }
        public int getId() { return id; }
        public String getJob() { return job; }

        public Attendance getAttendance() { return attendance; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String method) { this.paymentMethod = method; }

        public void setBankAccount(String account) { this.bankAccount = account; }
        public String getBankAccount() { return bankAccount; }

        // Abstract method
        abstract double calculateSalary();

        // New method for salary with overtime
        public double calculateSalaryWithOvertime() {
            double baseSalary = calculateSalary();
            if (this instanceof PartTimeEmployee) {
                PartTimeEmployee pte = (PartTimeEmployee) this;
                double overtimePay = attendance.getOvertimePay(pte.getHourlyRate());
                return baseSalary + overtimePay;
            }
            return baseSalary;
        }

        @Override
        public String toString() {
            return String.format("Employee[Name: %s, ID: %d, Job: %s, Salary: $%.2f]",
                    name, id, job, calculateSalary());
        }
    }
}
