// Attendance.java
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Attendance {
    private int employeeId;
    private Map<LocalDate, Boolean> attendance; // true = present, false = absent
    private Map<LocalDate, Integer> overtimeHours;
    private int sickLeaves;
    private int casualLeaves;

    public Attendance(int employeeId) {
        this.employeeId = employeeId;
        this.attendance = new HashMap<>();
        this.overtimeHours = new HashMap<>();
        this.sickLeaves = 12; // Annual sick leave entitlement
        this.casualLeaves = 15; // Annual casual leave entitlement
    }

    public void markAttendance(LocalDate date, boolean present) {
        attendance.put(date, present);
    }

    public void addOvertime(LocalDate date, int hours) {
        overtimeHours.put(date, hours);
    }

    public int getTotalOvertime() {
        return overtimeHours.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getOvertimePay(double hourlyRate) {
        // Overtime paid at 1.5x rate
        return getTotalOvertime() * hourlyRate * 1.5;
    }

    public int getAbsentDays() {
        return (int) attendance.values().stream().filter(present -> !present).count();
    }

    public void useSickLeave(int days) {
        if (sickLeaves >= days) {
            sickLeaves -= days;
        }
    }

    public void useCasualLeave(int days) {
        if (casualLeaves >= days) {
            casualLeaves -= days;
        }
    }

    // Getters
    public int getSickLeaves() { return sickLeaves; }
    public int getCasualLeaves() { return casualLeaves; }
}